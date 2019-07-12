package com.nimtego.plectrum.presentation.mvp.model.main_tab_model

import kotlin.collections.List

interface ParentTabModelContainer<C: ChildViewModel> {
    fun title(): String
    fun getModels(): List<C>
}