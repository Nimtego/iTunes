package com.nimtego.plectrum.presentation.old.main.artists

import com.nimtego.plectrum.presentation.old.main.fragments.MainTabsView
import com.nimtego.plectrum.presentation.old.main.model.ArtistModel

interface ArtistTabView : MainTabsView {
    fun render(artistModels: Collection<ArtistModel>)
}