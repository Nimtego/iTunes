package com.nimtego.plectrum.presentation.information_view.album

import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModelK
import com.nimtego.plectrum.presentation.mvp.BaseView

interface AlbumInformationViewK : BaseView {
    fun render(albumDetailsModel: AlbumDetailsModelK)
}