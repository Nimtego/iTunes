package com.nimtego.plectrum.presentation.mvp.presenters.navigation

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.manger.TabsProvider
import com.nimtego.plectrum.presentation.manger.UserSearchItemStorage
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.SearchNavigationView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import com.nimtego.plectrum.presentation.navigation.SearchTabScreenFabric
import com.nimtego.plectrum.presentation.navigation.Tab
import com.nimtego.plectrum.presentation.ui.auxiliary.SearchTabContainer
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import rx.Subscriber
import javax.inject.Inject

@InjectViewState
class SearchNavigationPresenter @Inject constructor(
        private val parentNavigationHandler: NavigationHandler,
        private val searchNavigationHandler: NavigationHandler,
        private val userSearchItemStorage: UserSearchItemStorage,
        private val searchTabScreenFabric: SearchTabScreenFabric,
        private val tabsProvider: TabsProvider
) : BaseNavigationPresenter<SearchNavigationView>() {

    private var parentRouter: Router? = null
    private var searchRouter: Router? = null
    private lateinit var navigationQualifier: String

    private var currentSearchSubscriber: CurrentSearchSubscriber? = null
    private var isSearchState: Boolean = false
    private var searchTabContainer: SearchTabContainer? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.navigationQualifier.let {
            this.parentRouter = parentNavigationHandler.getRouter(it)
            this.searchRouter = searchNavigationHandler.getRouter(it)
        }
        viewIsVisible(true)
    }

    override fun onBackPressed(): Boolean {
//        this.isSearchState = false
//        this.viewState.showSearchTabs(this.isSearchState)
        this.tabsProvider.overrideTabContainer(null)
        this.parentRouter?.exit()
        return true
    }

    override fun attachView(view: SearchNavigationView) {
        super.attachView(view)
        this.currentSearchSubscriber = CurrentSearchSubscriber()
        this.userSearchItemStorage.getCurrentSearchTextPublish()
                .subscribe(this.currentSearchSubscriber)
        //this.viewIsVisible(true)
    }

    private fun tabSelected(tab: Tab) {
        this.searchTabContainer = this.searchTabContainer?.copy(currentTabNumber = tab)
        //this.viewState.systemMessage("In tab selected qu - $navigationQualifier")
        this.searchRouter?.replaceScreen(
                this.searchTabScreenFabric.getScreensContainer(this.navigationQualifier).getScreen(tab)
        )
    }

    override fun detachView(view: SearchNavigationView) {
        super.detachView(view)
        this.currentSearchSubscriber?.unsubscribe()
        this.viewIsVisible(false)
    }

    fun viewIsVisible(visible: Boolean) {
        if (this.searchTabContainer == null) {
            this.searchTabContainer =  SearchTabContainer(
                    listTab = this.searchTabScreenFabric.getScreensContainer(
                            this.navigationQualifier
                    ).getTabs()
            ) {tab -> this@SearchNavigationPresenter.tabSelected(tab)}
        }
        if (visible) {
            this.currentSearchSubscriber = CurrentSearchSubscriber()
            this.userSearchItemStorage.getCurrentSearchTextPublish()
                    .subscribe(this.currentSearchSubscriber)
            this.tabsProvider.overrideTabContainer(this.searchTabContainer)
        }
        else  {
            this.currentSearchSubscriber?.unsubscribe()
            this.tabsProvider.overrideTabContainer(null)
        }
    }

    private fun navigateToSearch() {
        this.isSearchState = true
        //this.viewState.showSearchTabs(this.isSearchState)
        //this.searchRouter?.navigateTo(Screens.SearchContentScreen(this.navigationQualifier))
    }

    fun setNavigationQualifiers(tabNavigationQualifier:  String) {
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