package com.nimtego.plectrum.presentation.mvp.presenters.navigation

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.manger.UserSearchItemStorage
import com.nimtego.plectrum.presentation.mvp.presenters.base.BaseNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SearchTabNavPresenter @Inject constructor(
        private val navigationHandler: NavigationHandler
) : BaseNavigationPresenter<TabNavigationView>() {

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

    fun setNavigationQualifiers(tabNavigationQualifier: String) {
        this.navigationQualifier = tabNavigationQualifier
    }

    override fun onDestroy() {

    }

}