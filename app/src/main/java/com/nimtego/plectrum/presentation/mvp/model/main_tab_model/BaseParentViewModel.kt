package com.nimtego.plectrum.presentation.mvp.model.main_tab_model

import kotlin.collections.List

data class BaseParentViewModel<C: ChildViewModel>(
        val sectionViewModel: List<ParentTabModelContainer<C>>) {
}