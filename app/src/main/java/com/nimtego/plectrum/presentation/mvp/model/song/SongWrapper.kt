package com.nimtego.plectrum.presentation.mvp.model.song

import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel

data class SongWrapper(
        private val song: Song
) : ChildViewModel {

    override fun mainName(): String {
        return this.song.trackName
    }

    override fun minorName(): String {
        return this.song.artistName
    }

    override fun imageUrl(): String {
        return this.song.trackArtWorkUrl
    }

    override fun id(): String {
        return this.song.trackId.toString()
    }
}