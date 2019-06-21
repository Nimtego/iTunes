package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.ChildViewModel

@StateStrategyType(OneExecutionStateStrategy::class)
interface TabContentView : BaseView {
    @StateStrategyType(SkipStrategy::class)
    fun showViewState(data: BaseParentViewModel<ChildViewModel>)
    fun message(message: String)
    fun next(section: String)
}