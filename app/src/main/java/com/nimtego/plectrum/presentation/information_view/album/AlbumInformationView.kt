package com.nimtego.plectrum.presentation.information_view.album

import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModel
import com.nimtego.plectrum.presentation.mvp.view.BaseView

interface AlbumInformationView : BaseView {
    fun render(albumDetailsModel: AlbumDetailsModel)
}