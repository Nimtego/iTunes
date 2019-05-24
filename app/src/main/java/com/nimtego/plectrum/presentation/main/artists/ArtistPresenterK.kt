package com.nimtego.plectrum.presentation.main.artists

import com.nimtego.plectrum.presentation.main.model.ArtistModelK

interface ArtistPresenterK {
    fun itemClick(artistModel: ArtistModelK)
    fun search(response: String)
}