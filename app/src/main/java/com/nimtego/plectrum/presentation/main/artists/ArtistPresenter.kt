package com.nimtego.plectrum.presentation.main.artists

import com.nimtego.plectrum.presentation.main.model.ArtistModel

interface ArtistPresenter {
    fun itemClick(artistModel: ArtistModel)
    fun search(response: String)
}