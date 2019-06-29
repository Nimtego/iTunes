package com.nimtego.plectrum.presentation.old.information_view.artist

import com.nimtego.plectrum.presentation.old.main.model.AlbumModel

interface ArtistInformationPresenter {
    fun viewReady(artistNameForResponse: String)

    fun albumClicked(album: AlbumModel)
}