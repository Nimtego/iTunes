package com.nimtego.plectrum.presentation.manger

import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.ParentTabModelContainer

interface SectionItemStorage {
    fun getCurrentSection(): ParentTabModelContainer<ChildViewModel>?
    fun changeCurrentSection(currentSection: ParentTabModelContainer<ChildViewModel>)
}