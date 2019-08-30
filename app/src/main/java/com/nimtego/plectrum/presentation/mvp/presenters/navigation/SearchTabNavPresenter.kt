package com.nimtego.plectrum.presentation.mvp.presenters.navigation

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.manger.UserSearchItemStorage
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.SearchNavigationView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import com.nimtego.plectrum.presentation.navigation.NavigationHandlerVariable
import com.nimtego.plectrum.presentation.navigation.Screens
import ru.terrakok.cicerone.Router
import rx.Subscriber
import javax.inject.Inject

@InjectViewState
class SearchTabNavPresenter @Inject constructor(
        private val navigationHandler: NavigationHandler,
        private val userSearchItemStorage: UserSearchItemStorage
) : BaseNavigationPresenter<SearchNavigationView>() {

    private var router: Router? = null
    private lateinit var navigationQualifier: String

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.navigationQualifier.let {
            this.router = navigationHandler.getRouter(navigationQualifier)
        }
    }

    override fun onBackPressed(): Boolean {
        this.router?.exit()
        return true
    }

    fun viewIsVisible(visible: Boolean) {
        if (visible) {
//            this.currentSearchSubscriber = CurrentSearchSubscriber()
//            this.userSearchItemStorage.getCurrentSearchTextPublish()
//                    .subscribe(this.currentSearchSubscriber)
//            //this.viewState.showSearchTabs(this.isSearchState)
        } else {
//            this.currentSearchSubscriber?.unsubscribe()
        }
    }

    fun setNavigationQualifiers(tabNavigationQualifier: String) {
        this.navigationQualifier = tabNavigationQualifier
    }

    override fun onDestroy() {

    }

}