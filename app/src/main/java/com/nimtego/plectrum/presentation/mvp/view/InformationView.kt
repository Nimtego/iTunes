package com.nimtego.plectrum.presentation.mvp.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.model.information_view.SongDetailsModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface InformationView : BaseView {
    fun showViewState(data: ChildViewModel)
}