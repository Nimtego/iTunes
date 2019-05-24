package com.nimtego.plectrum.presentation.information_view.song

import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModelK
import com.nimtego.plectrum.presentation.mvp.BaseView

interface SongInformationView : BaseView {
    fun render(songDetailsModel: SongDetailsModelK)
}