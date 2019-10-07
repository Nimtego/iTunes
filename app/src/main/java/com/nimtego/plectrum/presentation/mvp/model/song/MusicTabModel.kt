package com.nimtego.plectrum.presentation.mvp.model.song

import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer

data class MusicTabModel(
        val titleKey: SectionsKey,
        val title: String,
        val modelList: List<ChildViewModel>
) : ParentTabModelContainer<ChildViewModel> {

    override fun titleKey(): SectionsKey {
        return this.titleKey
    }

    override fun title(): String {
        return this.title
    }

    override fun getModels(): List<ChildViewModel> {
        return  this.modelList
    }
}