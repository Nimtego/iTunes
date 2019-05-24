package com.nimtego.plectrum.presentation.main.fragments

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.ProgressView

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainTabsView : ProgressView{
    fun search(response: String)
    fun clearList()
    fun setCurrentSearch(response: String)
}