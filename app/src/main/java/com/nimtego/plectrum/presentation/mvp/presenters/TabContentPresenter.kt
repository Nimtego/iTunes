package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.data.entity.TabContentModel
import com.nimtego.plectrum.domain.interactor.TabContentInteractor
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.SectionViewModel
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.fragment.BottomNavigationFragment
import com.nimtego.plectrum.presentation.ui.widget.adapters.DashBoardTabAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router

@InjectViewState
class TabContentPresenter(
        router: Router,
        screenNumber: Int,
        private val interactor: TabContentInteractor
) : BasePresenter<TabContentView>(router, screenNumber), DashBoardTabAdapter.OnItemClickListener {

    private var tabContentModel: TabContentModel? = null

    override fun childItemClicked(id: String) {
        viewState.systemMessage(id)
    }



    override fun sectionClicked(sectionName: String) {
        router.navigateTo(Screens.MoreContentView(sectionName))
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
//        val data = BaseParentViewModel(listOf<ParentTabModelContainer<ChildViewModel>>(
//                SectionViewModel("Top album", tabContentModel.contentList.),
//                SectionViewModel("Top song", dataSongsModel.topSongs),
//                SectionViewModel("Hot song", dataSongsModel.hotTrack),
//                SectionViewModel("New music", dataSongsModel.newMusic))
//        )
        viewState.showViewState(data)

    }

    fun albumClicked(albumModel: Album) {
        //router.navigateTo(Screens.AlbumInformationDetail(albumModel.albumId.toString()))
    }

    fun songClicked(songModel: Song) {
        //router.navigateTo(Screens.SongInformationDetail(songModel.trackId.toString()))
    }
}
