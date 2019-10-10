package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer

@StateStrategyType(AddToEndSingleStrategy::class)
interface MoreSectionView : BaseView {
    fun showViewState(data: ParentTabModelContainer<ChildViewModel>)
}