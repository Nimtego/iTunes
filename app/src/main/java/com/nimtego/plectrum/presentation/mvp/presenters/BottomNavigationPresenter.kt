package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.domain.interactor.BottomNavigationInteractor
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import ru.terrakok.cicerone.Router

@InjectViewState
class BottomNavigationPresenter(router: Router,
                                screenNumber: Int,
                                private val interactor: BottomNavigationInteractor)
    : BasePresenter<MainBottomNavigationView>(router, screenNumber) {

}