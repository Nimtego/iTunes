package com.nimtego.plectrum.presentation.information_view.artist

import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK
import com.nimtego.plectrum.presentation.mvp.BaseView

interface ArtistInformationView : BaseView {
    fun render(artistDetailsModel: ArtistDetailsModelK)
}