package com.nimtego.plectrum.presentation.mvp.presenters.navigation

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.manger.UserSearchItemStorage
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.SearchNavigationView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.navigation.SearchTabScreenFabric
import ru.terrakok.cicerone.Router
import rx.Subscriber
import javax.inject.Inject

@InjectViewState
class SearchNavigationPresenter @Inject constructor(
        private val searchNavigationHandler: NavigationHandler,
        private val userSearchItemStorage: UserSearchItemStorage,
        private val searchTabScreenFabric: SearchTabScreenFabric
) : BaseNavigationPresenter<SearchNavigationView>() {

    private var searchRouter: Router? = null
    private lateinit var navigationQualifier: String
    private var currentTab: String? = null

    private var currentSearchSubscriber: CurrentSearchSubscriber? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.navigationQualifier.let {
            this.searchRouter = searchNavigationHandler.getRouter(it)
        }
        this.viewState.selectTab(0)
    }

    override fun onBackPressed(): Boolean {
        this.searchRouter?.exit()
        return true
    }

    override fun attachView(view: SearchNavigationView) {
        super.attachView(view)
        this.currentSearchSubscriber = CurrentSearchSubscriber()
        this.userSearchItemStorage.getCurrentSearchTextPublish()
                .subscribe(this.currentSearchSubscriber)
    }

    fun tabSelected(tab: String) {
        if (this.currentTab == null || this.currentTab != tab) {
            val screen = this.searchTabScreenFabric
                    .getScreensContainer(this.navigationQualifier).getScreen(tab)
            this.searchRouter?.replaceScreen(screen)
            this.currentTab = tab
        } else {
           //todo to realize reselected
        }
    }

    override fun detachView(view: SearchNavigationView) {
        super.detachView(view)
        this.currentSearchSubscriber?.unsubscribe()
    }

    private fun navigateToSearch() {
        this.searchRouter?.navigateTo(Screens.SearchContentScreen(this.navigationQualifier))
    }

    fun setNavigationQualifiers(tabNavigationQualifier: String) {
        this.navigationQualifier = tabNavigationQualifier
    }

    override fun onDestroy() {
        this.currentSearchSubscriber?.unsubscribe()
    }

    private inner class CurrentSearchSubscriber : Subscriber<String>() {
        override fun onCompleted() {}
        override fun onNext(result: String) {
            this@SearchNavigationPresenter.navigateToSearch()
        }

        override fun onError(e: Throwable) {}
    }

}