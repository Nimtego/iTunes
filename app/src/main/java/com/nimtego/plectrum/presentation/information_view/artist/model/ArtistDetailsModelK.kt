package com.nimtego.plectrum.presentation.information_view.artist.model

import com.nimtego.plectrum.presentation.main.model.AlbumModelK

data class ArtistDetailsModelK (
        val artistName: String? = null,
        val artistArtwork: String? = null,
        val artistId: Int = 0,
        val albums: List<AlbumModelK>? = null,
        val wikiInformation: String? = null)