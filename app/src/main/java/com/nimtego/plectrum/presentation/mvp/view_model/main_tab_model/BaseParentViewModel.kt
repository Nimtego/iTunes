package com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model

data class BaseParentViewModel<C: ChildViewModel>(
        val sectionViewModel: List<DashBoardModelContainer<C>>) {
}