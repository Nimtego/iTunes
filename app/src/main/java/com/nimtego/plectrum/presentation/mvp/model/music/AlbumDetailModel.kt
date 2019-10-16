package com.nimtego.plectrum.presentation.mvp.model.music

data class AlbumDetailModel(
        val albumName: String,
        val albumArtistName: String,
        val albumArtwork: String,
        val albumId: String,
        val albumPrice: String,
        val albumReleaseDate: String,
        val albumSongDetails: List<SongDetailModel> = emptyList())
