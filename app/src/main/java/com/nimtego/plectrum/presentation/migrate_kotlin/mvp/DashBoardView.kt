package com.nimtego.plectrum.presentation.migrate_kotlin.mvp

import com.nimtego.plectrum.data.entity.DashBoardModel
import com.nimtego.plectrum.presentation.mvp.ProgressView

interface DashBoardView : ProgressView {
    fun showViewState(dashboardModel: DashBoardModel)
    fun message(message: String?)
}