package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class TabNavigationPresenter @Inject constructor(
        private val router: Router
) : BaseNavigationPresenter<TabNavigationView>() {

    override fun onBackPressed(): Boolean {
        this.router.exit()
        return true
    }
}