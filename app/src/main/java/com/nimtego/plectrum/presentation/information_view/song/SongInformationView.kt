package com.nimtego.plectrum.presentation.information_view.song

import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModel
import com.nimtego.plectrum.presentation.mvp.BaseView

interface SongInformationView : BaseView {
    fun render(songDetailsModel: SongDetailsModel)
}