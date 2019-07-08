package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.data.entity.TabContentModel
import com.nimtego.plectrum.domain.interactor.TabContentInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.SectionViewModel
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.widget.adapters.ParentTabAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class TabContentPresenter
@Inject constructor(
        @Named(NavigationQualifiers.TAB_CONTENT_NAVIGATION)
        private val router: Router,
        private val interactor: TabContentInteractor
) : BasePresenter<TabContentView>(), ParentTabAdapter.OnItemClickListener {

    private var tabContentModel: TabContentModel? = null

    override fun childItemClicked(id: String) {
        viewState.systemMessage(id)
    }



    override fun sectionClicked(sectionName: String) {
        this.router.navigateTo(Screens.MoreContentScreen)
        viewState.systemMessage(sectionName)
    }

    override fun attachView(view: TabContentView) {
        super.attachView(view)
        tabContentModel?.let { showModel(it) }
    }

    fun viewIsReady(tab: String) {
        tabContentModel?.let { showModel(it) }.run {
            interactor.execute(object : DisposableObserver<TabContentModel>() {
                override fun onComplete() {
                    Log.i("Presenter", "onComplete()")
                }

                override fun onNext(tabContentModel: TabContentModel) {
                    Log.i("Presenter", "onnext")
                    this@TabContentPresenter.tabContentModel = tabContentModel
                    this@TabContentPresenter.showModel(tabContentModel)
                }

                override fun onError(e: Throwable) {
                    Log.i("Presenter", "onerror $e")
//                this@BottomNavigationPresenter.hideViewLoading()
//                this@BottomNavigationPresenter.toast("error" + e.localizedMessage)
//                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }
            }, TabContentInteractor.Params.forRequest(tab))
        }
    }

    private fun showModel(tabContentModel: TabContentModel) {
        //todo create res for title or...
        val listContent = tabContentModel.contentList
        val data =  BaseParentViewModel(listContent.map {
            SectionViewModel(it.title(), it.getModels())
        })
        viewState.showViewState(data)

    }

    fun albumClicked(albumModel: Album) {
        //router.navigateTo(Screens.AlbumInformationDetail(albumModel.albumId.toString()))
    }

    fun songClicked(songModel: Song) {
        //router.navigateTo(Screens.SongInformationDetail(songModel.trackId.toString()))
    }

    fun onBackPressed(): Boolean {
        //todo
        return true
    }
}
