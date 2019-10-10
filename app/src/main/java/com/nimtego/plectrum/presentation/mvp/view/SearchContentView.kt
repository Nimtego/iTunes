package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchContentView : ProgressView {
    fun showViewState(data: List<ChildViewModel>)
}