package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface MainBottomNavigationView : ProgressView {
    @StateStrategyType(SkipStrategy::class)
    fun withInnerTopNavigation(tabs: List<String>)
    @StateStrategyType(SkipStrategy::class)
    fun closeInnerTopNavigation()
}