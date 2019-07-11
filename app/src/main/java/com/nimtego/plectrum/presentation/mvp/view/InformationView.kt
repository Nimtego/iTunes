package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.model.information_view.SongDetailsModel

@StateStrategyType(OneExecutionStateStrategy::class)
interface InformationView : BaseView {
    @StateStrategyType(SkipStrategy::class)
    fun showViewState(data: SongDetailsModel)
}