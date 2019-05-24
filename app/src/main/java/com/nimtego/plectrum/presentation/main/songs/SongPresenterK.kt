package com.nimtego.plectrum.presentation.main.songs

import com.nimtego.plectrum.presentation.main.model.SongModelK

interface SongPresenterK {
    fun itemClick(artistModel: SongModelK)
    fun search(response: String)
}