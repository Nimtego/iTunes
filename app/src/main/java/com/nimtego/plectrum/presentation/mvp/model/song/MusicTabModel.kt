package com.nimtego.plectrum.presentation.mvp.model.song

import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer

data class MusicTabModel(
        val title: String,
        val parentList: List<ChildViewModel>
) : ParentTabModelContainer<ChildViewModel> {

    override fun title(): String {
        return this.title
    }

    override fun getModels(): List<ChildViewModel> {
        return  this.parentList
    }
}