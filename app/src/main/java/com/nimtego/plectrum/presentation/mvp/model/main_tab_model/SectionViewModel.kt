package com.nimtego.plectrum.presentation.mvp.model.main_tab_model

import kotlin.collections.List

data class SectionViewModel<M : ChildViewModel>(
        val title: String,
        val parentList: List<M>) : ParentTabModelContainer<M> {
    override fun title(): String {
        return title
    }
    override fun getModels(): List<M> {
        return parentList
    }
}