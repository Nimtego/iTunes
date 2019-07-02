package com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model

interface DashBoardModelContainer<C: ChildViewModel> {
    fun title(): String
    fun getModels(): List<C>
}