package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.domain.interactor.BottomNavigationInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class BottomNavigationPresenter
@Inject constructor(
        @Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)
        val bottomNavigationRouter: Router,
        screenNumber: Int,
        private val interactor: BottomNavigationInteractor
) : BasePresenter<MainBottomNavigationView>(bottomNavigationRouter, screenNumber) {

    @field:[Inject Named(NavigationQualifiers.APP_NAVIGATION)]
    internal lateinit var appRouter: Router

    fun replaceFragment(screenName: SupportAppScreen) {
        this.bottomNavigationRouter.replaceScreen(screenName)
    }

    fun onBackPressed(): Boolean {
        this.bottomNavigationRouter.exit()
        return true
    }

}