package com.nimtego.plectrum.presentation.information_view.artist

import com.nimtego.plectrum.presentation.main.model.AlbumModelK

interface ArtistInformationPresenterK {
    fun viewReady(artistNameForResponse: String)

    fun albumClicked(album: AlbumModelK)
}