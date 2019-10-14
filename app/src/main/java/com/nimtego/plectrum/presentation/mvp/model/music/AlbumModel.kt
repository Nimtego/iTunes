package com.nimtego.plectrum.presentation.mvp.model.music

data class AlbumModel(
        val albumName: String,
        val albumArtistName: String,
        val albumArtwork: String,
        val albumId: String,
        val albumPrice: String,
        val albumReleaseDate: String,
        val albumSongs: List<SongModel> = emptyList())
