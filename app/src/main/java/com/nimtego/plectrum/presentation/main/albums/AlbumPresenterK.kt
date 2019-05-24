package com.nimtego.plectrum.presentation.main.albums

import com.nimtego.plectrum.presentation.main.model.AlbumModel

interface AlbumPresenterK {
    fun albumClicked(albumModel: AlbumModel)
    fun search(response: String)
}