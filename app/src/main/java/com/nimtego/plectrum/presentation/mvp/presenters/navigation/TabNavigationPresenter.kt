package com.nimtego.plectrum.presentation.mvp.presenters.navigation

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.manger.UserSearchItemStorage
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import com.nimtego.plectrum.presentation.navigation.Screens
import ru.terrakok.cicerone.Router
import rx.Subscriber
import javax.inject.Inject

@InjectViewState
class TabNavigationPresenter @Inject constructor(
        private val router: Router,
        private val userSearchItemStorage: UserSearchItemStorage
) : BaseNavigationPresenter<TabNavigationView>() {

    private var currentSearchSubscriber: CurrentSearchSubscriber? = null
    private lateinit var navigationQualifier: String
    private var isSearchState: Boolean = false

    override fun onBackPressed(): Boolean {
        this.isSearchState = false
        this.viewState.showSearchTabs(this.isSearchState)
        this.router.exit()
        return true
    }

    override fun attachView(view: TabNavigationView) {
        super.attachView(view)
        this.currentSearchSubscriber = CurrentSearchSubscriber()
        this.userSearchItemStorage.getCurrentSearchTextPublish()
                .subscribe(this.currentSearchSubscriber)
    }

    override fun detachView(view: TabNavigationView) {
        super.detachView(view)
        this.currentSearchSubscriber?.unsubscribe()
    }

    fun viewIsVisible(visible: Boolean) {
        if (visible) {
            this.currentSearchSubscriber = CurrentSearchSubscriber()
            this.userSearchItemStorage.getCurrentSearchTextPublish()
                    .subscribe(this.currentSearchSubscriber)
            this.viewState.showSearchTabs(this.isSearchState)
        }
        else  {
            this.currentSearchSubscriber?.unsubscribe()
        }
    }

    private fun navigateToSearch() {
        this.isSearchState = true
        this.viewState.showSearchTabs(this.isSearchState)
        this.router.navigateTo(Screens.SearchNavigationScreen(this.navigationQualifier))
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
            this@TabNavigationPresenter.navigateToSearch()
        }
        override fun onError(e: Throwable) {}
    }

}