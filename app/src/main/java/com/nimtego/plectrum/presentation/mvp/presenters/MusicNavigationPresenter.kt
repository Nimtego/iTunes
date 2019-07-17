package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MusicNavigationPresenter @Inject constructor(
        private val musicTabRouter: Router,
        private val bottomBarRouter: Router
) : BaseNavigationPresenter<TabNavigationView>() {

    fun onBackPressed(): Boolean {
        this.musicTabRouter.exit()
        return true
    }
}