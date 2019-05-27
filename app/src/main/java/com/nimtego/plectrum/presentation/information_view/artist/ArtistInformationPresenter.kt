package com.nimtego.plectrum.presentation.information_view.artist

import com.nimtego.plectrum.presentation.main.model.AlbumModel

interface ArtistInformationPresenter {
    fun viewReady(artistNameForResponse: String)

    fun albumClicked(album: AlbumModel)
}