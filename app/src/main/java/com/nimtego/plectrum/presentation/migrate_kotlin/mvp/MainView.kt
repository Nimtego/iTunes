package com.nimtego.plectrum.presentation.migrate_kotlin.mvp

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : ProgressView {
    fun render(response: String)
}