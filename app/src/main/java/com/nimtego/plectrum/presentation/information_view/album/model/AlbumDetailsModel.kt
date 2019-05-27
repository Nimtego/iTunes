package com.nimtego.plectrum.presentation.information_view.album.model

import com.nimtego.plectrum.presentation.main.model.SongModel

data class AlbumDetailsModel (
        val albumName: String? = null,
        val albumArtistName: String? = null,
        val albumArtwork: String? = null,
        val albumId: Int = 0,
        val albumArtWorkUrl: String? = null,
        val songs: List<SongModel>? = null,
        val collectionPrice: Double? = null,
        val releaseDate: String? = null,
        val wikiInformation: String? = null)