package com.nimtego.plectrum.presentation.main.albums

import com.nimtego.plectrum.presentation.main.fragments.MainTabsView
import com.nimtego.plectrum.presentation.main.model.AlbumModelK
import com.nimtego.plectrum.presentation.mvp.BaseView

interface AlbumTabView : MainTabsView {
    fun render(albumModel: Collection<AlbumModelK>)
}