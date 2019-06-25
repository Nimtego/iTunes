package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.domain.interactor.DashBoardInteractor
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import ru.terrakok.cicerone.Router

@InjectViewState
class BottomNavigationPresenter(router: Router,
                                screenNumber: Int,
                                private val interactor: DashBoardInteractor)
    : BasePresenter<MainBottomNavigationView>(router, screenNumber) {

    fun viewIsReady() {

    }

    fun albumClicked(albumModel: Album) {
        //  router.navigateTo(Screens.AlbumInformationDetail(albumModel.albumId.toString()))
    }

    fun songClicked(songModel: Song) {
        //  router.navigateTo(Screens.SongInformationDetail(songModel.trackId.toString()))
    }

}