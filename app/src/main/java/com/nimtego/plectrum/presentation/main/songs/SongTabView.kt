package com.nimtego.plectrum.presentation.main.songs

import com.nimtego.plectrum.presentation.main.fragments.MainTabsView
import com.nimtego.plectrum.presentation.main.model.SongModelK

interface SongTabView : MainTabsView {
    fun render(songModels: Collection<SongModelK>)
}