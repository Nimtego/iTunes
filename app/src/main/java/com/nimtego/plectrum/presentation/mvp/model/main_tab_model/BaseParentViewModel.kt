package com.nimtego.plectrum.presentation.mvp.model.main_tab_model

data class BaseParentViewModel<C: ChildViewModel>(
        val sectionViewModel: List<ParentTabModelContainer<C>>) {
}