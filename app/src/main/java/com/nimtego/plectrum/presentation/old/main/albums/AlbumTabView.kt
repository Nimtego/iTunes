package com.nimtego.plectrum.presentation.old.main.albums

import com.nimtego.plectrum.presentation.old.main.fragments.MainTabsView
import com.nimtego.plectrum.presentation.old.main.model.AlbumModel

interface AlbumTabView : MainTabsView {
    fun render(albumModel: Collection<AlbumModel>)
}