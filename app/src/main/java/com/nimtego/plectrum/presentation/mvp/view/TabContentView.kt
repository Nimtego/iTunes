package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer

@StateStrategyType(OneExecutionStateStrategy::class)
interface TabContentView : BaseView {
    @StateStrategyType(SkipStrategy::class)
    fun showViewState(data: List<ParentTabModelContainer<ChildViewModel>>)
}