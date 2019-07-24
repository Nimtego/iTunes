package com.nimtego.plectrum.presentation.mvp.model.information_view

import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel

data class ArtistDetailsModelK (
        val artistName: String? = null,
        val artistArtwork: String? = null,
        val artistId: Int = 0,
        val albums: List<AlbumModel>? = null,
        val wikiInformation: String? = null)