package com.nimtego.plectrum.data.entity

import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel

data class TabChildViewModel(
        val mainName: String,
        val minorName: String,
        val imageUrl: String,
        val id: String
) : ChildViewModel {
    override fun mainName(): String {
        return this.mainName
    }

    override fun minorName(): String {
        return this.minorName
    }

    override fun imageUrl(): String {
        return this.imageUrl
    }

    override fun id(): String {
        return this.id
    }
}