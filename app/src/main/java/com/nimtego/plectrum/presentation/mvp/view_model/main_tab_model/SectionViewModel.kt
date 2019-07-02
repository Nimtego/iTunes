package com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model

data class SectionViewModel<M : ChildViewModel>(
        val title: String,
        val parentList: List<M>) : DashBoardModelContainer<M> {
    override fun title(): String {
        return title
    }
    override fun getModels(): List<M> {
        return parentList
    }
}