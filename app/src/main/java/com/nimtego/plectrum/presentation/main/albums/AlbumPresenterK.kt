package com.nimtego.plectrum.presentation.main.albums

import com.nimtego.plectrum.presentation.main.model.AlbumModelK

interface AlbumPresenterK {
    fun albumClicked(albumModel: AlbumModelK)
    fun search(response: String)
}