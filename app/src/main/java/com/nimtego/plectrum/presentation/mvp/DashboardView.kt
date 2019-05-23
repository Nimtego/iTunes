package com.nimtego.plectrum.presentation.mvp

import com.nimtego.plectrum.presentation.migrate_kotlin.view_model.DashboardViewModel

interface DashboardView : ProgressView {
    fun showViewState(dashboardViewModel: DashboardViewModel)
}