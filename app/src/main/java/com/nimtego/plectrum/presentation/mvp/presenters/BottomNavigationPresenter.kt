package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

@InjectViewState
class BottomNavigationPresenter @Inject constructor(
        private val bottomNavigationRouter: Router,
        private val appRouter: Router
) : BasePresenter<MainBottomNavigationView>() {

    fun replaceFragment(screenName: SupportAppScreen) {
        this.bottomNavigationRouter.replaceScreen(screenName)
    }

    fun onBackPressed(): Boolean {
        this.bottomNavigationRouter.exit()
        return true
    }

}