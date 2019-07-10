package com.nimtego.plectrum.presentation.manger

import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.ParentTabModelContainer

class MainChoiceItemStorage : MainItemStorage {

    private var currentSection: ParentTabModelContainer<ChildViewModel>? = null
    private var currentChildItem: ChildViewModel? = null


    override fun getCurrentSection(): ParentTabModelContainer<ChildViewModel>? {
        return this.currentSection
    }

    override fun getCurrentChildItem(): ChildViewModel? {
        return this.currentChildItem
    }

    override fun changeCurrentSection(currentSection: ParentTabModelContainer<ChildViewModel>) {
        this.currentSection = currentSection
    }

    override fun changeCurrentChildItem(currentChildItem: ChildViewModel) {
        this.currentChildItem = currentChildItem
    }
}