package com.nimtego.plectrum.presentation.main.albums

import com.nimtego.plectrum.presentation.main.fragments.MainTabsView
import com.nimtego.plectrum.presentation.main.model.AlbumModel

interface AlbumTabView : MainTabsView {
    fun render(albumModel: Collection<AlbumModel>)
}