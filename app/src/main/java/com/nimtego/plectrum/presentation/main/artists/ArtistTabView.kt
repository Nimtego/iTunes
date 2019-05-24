package com.nimtego.plectrum.presentation.main.artists

import com.nimtego.plectrum.presentation.main.fragments.MainTabsView
import com.nimtego.plectrum.presentation.main.model.ArtistModel

interface ArtistTabView : MainTabsView {
    fun render(artistModels: Collection<ArtistModel>)
}