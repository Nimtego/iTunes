package com.nimtego.plectrum.presentation.information_view.artist.model

import com.nimtego.plectrum.presentation.main.model.AlbumModel

data class ArtistDetailsModelK (
        val artistName: String? = null,
        val artistArtwork: String? = null,
        val artistId: Int = 0,
        val albums: List<AlbumModel>? = null,
        val wikiInformation: String? = null)