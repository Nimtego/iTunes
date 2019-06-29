package com.nimtego.plectrum.presentation.old.information_view.song

import com.nimtego.plectrum.presentation.old.information_view.song.model.SongDetailsModel
import com.nimtego.plectrum.presentation.mvp.view.BaseView

interface SongInformationView : BaseView {
    fun render(songDetailsModel: SongDetailsModel)
}