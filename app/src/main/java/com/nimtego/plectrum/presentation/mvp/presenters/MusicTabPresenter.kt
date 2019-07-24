package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.PopularMusicInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.model.song.MusicTabModel
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
        private val interactor: PopularMusicInteractor
) : BasePresenter<TabContentView>(interactor), ParentTabAdapter.OnItemClickListener {

    // FIXME
    //  APP_ROUTER
    //  @Named(NavigationQualifiers.APP_NAVIGATION)
    //  private val appRouter: Route,
    //  Temporary ugly hack,
    //  because resolve crash(FragmentManager is already executing transactions).
    //  We need to redefine the concept of navigation.

    private var songsModel: BaseParentViewModel<ChildViewModel>? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.viewState.systemMessage("First attach")
    }

    fun viewIsReady(containerName: String) {
        this.songsModel?.let{
            showModel()
        } ?: run {
            interactor.execute(object : DisposableObserver<BaseParentViewModel<ChildViewModel>>() {
                override fun onComplete() {
                    Log.i("Presenter", "onComplete()")
                }

                override fun onNext(songs: BaseParentViewModel<ChildViewModel>) {
                    Log.i("Presenter", "onnext")
                    this@MusicTabPresenter.songsModel = songs
                    this@MusicTabPresenter.showModel()
                }

                override fun onError(e: Throwable) {
                    Log.i("Presenter", "onerror $e")
//                this@BottomNavigationPresenter.hideViewLoading()
//                this@BottomNavigationPresenter.toast("error" + e.localizedMessage)
//                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }
            }, PopularMusicInteractor.Params.forRequest(containerName))
        }
    }

    private fun showModel() {
        this.songsModel?.let {
            this.viewState.showViewState(it)
        }
    }

    override fun sectionClicked(section: ParentTabModelContainer<ChildViewModel>) {
        this.itemStorage.changeCurrentSection(section)
        this.tabContentRouter.navigateTo(
                Screens.MoreContentScreen(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
        )
    }

    override fun childItemClicked(childViewModel: ChildViewModel) {
        this.itemStorage.changeCurrentChildItem(childViewModel)
        this.tabContentRouter.navigateTo(
                Screens.ItemInformationScreen(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
        )
    }

    fun onBackPressed() {
        this.tabContentRouter.exit()
    }
}