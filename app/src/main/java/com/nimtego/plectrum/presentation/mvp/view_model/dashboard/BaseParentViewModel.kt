package com.nimtego.plectrum.presentation.mvp.view_model.dashboard

data class BaseParentViewModel<C: ChildViewModel>(
        val sectionViewModel: List<DashBoardModelContainer<C>>) {
}