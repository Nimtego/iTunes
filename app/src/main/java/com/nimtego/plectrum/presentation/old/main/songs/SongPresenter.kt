package com.nimtego.plectrum.presentation.old.main.songs

import com.nimtego.plectrum.presentation.old.main.model.SongModel

interface SongPresenter {
    fun itemClick(artistModel: SongModel)
    fun search(response: String)
}