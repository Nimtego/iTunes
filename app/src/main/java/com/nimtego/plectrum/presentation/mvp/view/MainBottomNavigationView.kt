package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.navigation.Tab
import com.nimtego.plectrum.presentation.ui.auxiliary.TabContainer

@StateStrategyType(OneExecutionStateStrategy::class)
interface MainBottomNavigationView : ProgressView {
    @StateStrategyType(SkipStrategy::class)
    fun initSearchTabNavigation(tabContainer: TabContainer?)
}