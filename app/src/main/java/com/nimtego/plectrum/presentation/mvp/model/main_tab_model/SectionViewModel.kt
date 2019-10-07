package com.nimtego.plectrum.presentation.mvp.model.main_tab_model

import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import kotlin.collections.List


data class SectionViewModel<M : ChildViewModel>(
        val titleKey: SectionsKey,
        val title: String,
        val parentList: List<M>
) : ParentTabModelContainer<M> {

    override fun titleKey(): SectionsKey {
        return this.titleKey
    }

    override fun title(): String {
        return title
    }
    override fun getModels(): List<M> {
        return parentList
    }
}