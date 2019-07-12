package com.nimtego.plectrum.presentation.mvp.model.main_tab_model

import kotlin.collections.List

data class List<C: ChildViewModel>(
        val sectionViewModel: List<ParentTabModelContainer<C>>) {
}