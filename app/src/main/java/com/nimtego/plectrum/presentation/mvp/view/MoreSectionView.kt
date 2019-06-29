package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.data.entity.Song

@StateStrategyType(OneExecutionStateStrategy::class)
interface MoreSectionView : BaseView {
    @StateStrategyType(SkipStrategy::class)
    fun showViewState(data: List<Song>)

    fun message(message: String)
}