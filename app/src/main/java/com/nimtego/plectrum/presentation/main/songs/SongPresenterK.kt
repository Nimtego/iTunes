package com.nimtego.plectrum.presentation.main.songs

import com.nimtego.plectrum.presentation.main.model.SongModel

interface SongPresenterK {
    fun itemClick(artistModel: SongModel)
    fun search(response: String)
}