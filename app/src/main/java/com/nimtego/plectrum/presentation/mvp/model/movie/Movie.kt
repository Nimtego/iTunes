package com.nimtego.plectrum.presentation.mvp.model.movie

data class Movie(
        val albumId: Int,
        val albumArtistId: Int,
        val albumName: String,
        val albumRealiseDate: String,
        val albumTrackCount: Int,
        val albumArtWorkUrl: String,
        val albumArtistName: String,
        val albumPrice: Double
)