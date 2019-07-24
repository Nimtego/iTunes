package com.nimtego.plectrum.presentation.mvp.model.song

import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel

data class AlbumWrapperModel(
        private val album: Album
) : ChildViewModel {

    override fun mainName(): String {
        return this.album.albumName
    }

    override fun minorName(): String {
        return this.album.albumArtistName
    }

    override fun imageUrl(): String {
        return this.album.albumArtWorkUrl
    }

    override fun id(): String {
        return this.album.albumId.toString()
    }
}