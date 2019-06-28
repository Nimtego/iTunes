package com.nimtego.plectrum.presentation.old.main.artists

import com.nimtego.plectrum.presentation.old.main.model.ArtistModel

interface ArtistPresenter {
    fun itemClick(artistModel: ArtistModel)
    fun search(response: String)
}