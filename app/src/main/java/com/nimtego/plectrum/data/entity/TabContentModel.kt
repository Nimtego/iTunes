package com.nimtego.plectrum.data.entity

import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.DashBoardModelContainer

data class TabContentModel(val contentList: List<DashBoardModelContainer<ChildViewModel>>) {
}