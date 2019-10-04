package com.nimtego.plectrum.presentation.mvp.model.main_tab_model

import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import kotlin.collections.List

interface ParentTabModelContainer<C: ChildViewModel> {
    fun titleKey(): SectionsKey
    fun title(): String
    fun getModels(): List<C>
}