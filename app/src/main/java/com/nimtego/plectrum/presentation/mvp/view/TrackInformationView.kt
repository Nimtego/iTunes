package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.model.song.Song

@StateStrategyType(OneExecutionStateStrategy::class)
interface TrackInformationView : BaseView {
    fun showViewState(data: Song)
}