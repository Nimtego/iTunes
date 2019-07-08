package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.BottomNavigationInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class MusicNavigationPresenter
@Inject constructor(
        @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
        val musicTabNavigationRouter: Router,
        screenNumber: Int,
        private val interactor: BottomNavigationInteractor
) : BasePresenter<TabNavigationView>(musicTabNavigationRouter, screenNumber){
    // MARK: - Properties

    @field:[Inject Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)]
    internal lateinit var bottomBarRouter: Router


// MARK: - Methods

    fun onBackPressed(): Boolean {
        this.bottomBarRouter.exit()
        return true
    }
}