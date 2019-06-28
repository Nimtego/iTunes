package com.nimtego.plectrum.presentation.old.information_view.artist

import com.nimtego.plectrum.presentation.old.information_view.artist.model.ArtistDetailsModelK
import com.nimtego.plectrum.presentation.mvp.view.BaseView

interface ArtistInformationView : BaseView {
    fun render(artistDetailsModel: ArtistDetailsModelK)
}