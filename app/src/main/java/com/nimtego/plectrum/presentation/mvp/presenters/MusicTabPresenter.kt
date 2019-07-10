package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.TabContentModel
import com.nimtego.plectrum.domain.interactor.TabContentInteractor
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.SectionViewModel
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.widget.adapters.ParentTabAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MusicTabPresenter @Inject constructor(
        private val tabContentRouter: Router,
        private val appRouter: Router,
        private val itemStorage: MainItemStorage,
        private val interactor: TabContentInteractor
) : BasePresenter<TabContentView>(), ParentTabAdapter.OnItemClickListener {

    // FIXME
    //  APP_ROUTER
    //  @Named(NavigationQualifiers.APP_NAVIGATION)
    //  private val appRouter: Route,
    //  Temporary ugly hack,
    //  because resolve crash(FragmentManager is already executing transactions).
    //  We need to redefine the concept of navigation.

    private var tabContentModel: TabContentModel? = null

    override fun sectionClicked(section: ParentTabModelContainer<ChildViewModel>) {
        this.itemStorage.changeCurrentSection(section)
        this.tabContentRouter.navigateTo(Screens.MoreContentScreen)
    }

    override fun childItemClicked(childViewModel: ChildViewModel) {
        this.itemStorage.changeCurrentChildItem(childViewModel)
        this.tabContentRouter.navigateTo(Screens.ItemInformationScreen)
    }


    fun viewIsReady(containerName: String) {
        tabContentModel?.let { showModel(it) }.run {
            interactor.execute(object : DisposableObserver<TabContentModel>() {
                override fun onComplete() {
                    Log.i("Presenter", "onComplete()")
                }

                override fun onNext(tabContentModel: TabContentModel) {
                    Log.i("Presenter", "onnext")
                    this@MusicTabPresenter.tabContentModel = tabContentModel
                    this@MusicTabPresenter.showModel(tabContentModel)
                }

                override fun onError(e: Throwable) {
                    Log.i("Presenter", "onerror $e")
//                this@BottomNavigationPresenter.hideViewLoading()
//                this@BottomNavigationPresenter.toast("error" + e.localizedMessage)
//                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }
            }, TabContentInteractor.Params.forRequest(containerName))

        }
    }

    private fun showModel(tabContentModel: TabContentModel) {
        //todo create res for title or...
        val listContent = tabContentModel.contentList
        val data = BaseParentViewModel(listContent.map {
            SectionViewModel(it.title(), it.getModels())
        })
        viewState.showViewState(data)
    }

    fun onBackPressed() {
        this.tabContentRouter.exit()
    }
}