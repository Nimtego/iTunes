package com.nimtego.plectrum.presentation.main.artists

import com.nimtego.plectrum.presentation.main.fragments.MainTabsView
import com.nimtego.plectrum.presentation.main.model.AlbumModelK

interface ArtistTabView : MainTabsView {
    fun render(artistModels: Collection<AlbumModelK>)
}