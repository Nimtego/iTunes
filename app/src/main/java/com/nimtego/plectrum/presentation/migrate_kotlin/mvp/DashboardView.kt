package com.nimtego.plectrum.presentation.migrate_kotlin.mvp

import com.nimtego.plectrum.presentation.migrate_kotlin.view_model.DashboardViewModel
import com.nimtego.plectrum.presentation.mvp.ProgressView

interface DashboardView : ProgressView {
    fun showViewState(dashboardViewModel: DashboardViewModel)
}