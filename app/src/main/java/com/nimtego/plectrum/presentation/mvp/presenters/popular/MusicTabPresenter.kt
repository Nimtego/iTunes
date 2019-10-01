package com.nimtego.plectrum.presentation.mvp.presenters.popular

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.popular.PopularMusicInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.presenters.base.BasePresenter
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.widget.adapters.ParentTabAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MusicTabPresenter @Inject constructor(
        private val router: Router,
        private val itemStorage: MainItemStorage,
        private val interactor: PopularMusicInteractor
) : BasePresenter<TabContentView>(), ParentTabAdapter.OnItemClickListener {

    private var songsModel: BaseParentViewModel<ChildViewModel>? = null

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
            }, PopularMusicInteractor.Params.forRequestWithSize(containerName, 5))
        }
    }

    private fun showModel() {
        this.songsModel?.let {
            this.viewState.showViewState(it)
        }
    }

    override fun sectionClicked(section: ParentTabModelContainer<ChildViewModel>) {
        this.itemStorage.changeCurrentSection(section)
        this.router.navigateTo(
                Screens.MoreContentScreen(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
        )
    }

    override fun childItemClicked(childViewModel: ChildViewModel) {
        this.itemStorage.changeCurrentChildItem(childViewModel)
        this.router.navigateTo(
                Screens.ItemInformationScreen(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
        )
    }

    fun onBackPressed() {
        this.router.exit()
    }
}