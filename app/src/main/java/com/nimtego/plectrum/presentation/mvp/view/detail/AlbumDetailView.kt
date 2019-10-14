package com.nimtego.plectrum.presentation.mvp.view.detail

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import com.nimtego.plectrum.presentation.mvp.view.ProgressView

@StateStrategyType(AddToEndSingleStrategy::class)
interface AlbumDetailView : ProgressView {
    fun showViewState(albumModel: AlbumModel)
}