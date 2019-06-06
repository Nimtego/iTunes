package com.nimtego.plectrum.presentation.mvp.view_model.dashboard

interface DashBoardModelContainer<C: ChildViewModel> {
    fun title(): String
    fun getModels(): List<C>
}