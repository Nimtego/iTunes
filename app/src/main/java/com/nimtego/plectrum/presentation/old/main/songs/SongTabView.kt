package com.nimtego.plectrum.presentation.old.main.songs

import com.nimtego.plectrum.presentation.old.main.fragments.MainTabsView
import com.nimtego.plectrum.presentation.old.main.model.SongModel

interface SongTabView : MainTabsView {
    fun render(songModels: Collection<SongModel>)
}