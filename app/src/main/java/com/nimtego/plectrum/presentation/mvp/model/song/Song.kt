package com.nimtego.plectrum.presentation.mvp.model.song

data class Song (
    val artistId: Int,
    val collectionId: Int,
    val trackId: Int,
    val artistName: String,
    val wrapperType: String,
    val trackName: String,
    val trackPrice: Double,
    val trackArtWorkUrl: String,
    val trackTimeMillis: Int)