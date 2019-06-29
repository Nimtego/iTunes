package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : ProgressView {
    fun render(response: String)
}