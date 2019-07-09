package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MovieNavigationPresenter @Inject constructor(
        private val movieTabRouter: Router,
        private val bottomBarRouter: Router
) : BasePresenter<TabNavigationView>() {

    fun onBackPressed(): Boolean {
        this.movieTabRouter.exit()
        return true
    }
}