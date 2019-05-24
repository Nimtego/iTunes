package com.nimtego.plectrum.presentation.migrate_kotlin.mvp

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.BaseView

@StateStrategyType(OneExecutionStateStrategy::class)
interface ProgressView : BaseView {
    fun showProgress()
    fun hideProgress()
}