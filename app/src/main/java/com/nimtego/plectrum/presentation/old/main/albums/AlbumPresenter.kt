package com.nimtego.plectrum.presentation.old.main.albums

import com.nimtego.plectrum.presentation.old.main.model.AlbumModel

interface AlbumPresenter {
    fun albumClicked(albumModel: AlbumModel)
    fun search(response: String)
}