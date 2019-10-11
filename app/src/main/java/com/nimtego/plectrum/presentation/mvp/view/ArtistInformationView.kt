package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface ArtistInformationView : BaseView {
    fun showViewState(artistModel: ArtistModel)
}