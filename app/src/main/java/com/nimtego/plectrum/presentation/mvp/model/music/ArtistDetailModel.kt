package com.nimtego.plectrum.presentation.mvp.model.music

data class ArtistDetailModel(
        val artistName: String,
        val artistArtwork: String,
        val primaryGenreName: String,
        val artistViewUrl: String,
        val artistId: String,
        val artistAlbumDetails: List<AlbumDetailModel> = emptyList())