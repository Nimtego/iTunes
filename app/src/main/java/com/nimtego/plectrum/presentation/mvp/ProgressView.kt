package com.nimtego.plectrum.presentation.mvp

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface ProgressView : BaseView {
    fun showProgress()
    fun hideProgress()
}