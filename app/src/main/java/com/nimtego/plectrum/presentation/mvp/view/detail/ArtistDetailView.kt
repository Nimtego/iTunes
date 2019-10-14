package com.nimtego.plectrum.presentation.mvp.view.detail

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel
import com.nimtego.plectrum.presentation.mvp.view.ProgressView

@StateStrategyType(AddToEndSingleStrategy::class)
interface ArtistDetailView : ProgressView {
    fun showViewState(artistModel: ArtistModel)
}