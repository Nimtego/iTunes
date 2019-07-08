package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class BookNavigationPresenter @Inject constructor(
        private val bookTabRouter: Router,
        private val bottomBarRouter: Router
) : BasePresenter<TabNavigationView>() {


    fun onBackPressed(): Boolean {
        this.bookTabRouter.exit()
        return true
    }
}