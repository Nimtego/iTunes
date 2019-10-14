package com.nimtego.plectrum.presentation.mvp.model.music

data class ArtistModel(
        val artistName: String,
        val artistArtwork: String,
        val primaryGenreName: String,
        val artistViewUrl: String,
        val artistId: String,
        val artistAlbums: List<AlbumModel> = emptyList())