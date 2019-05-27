package com.nimtego.plectrum.data.entity

data class Album(
    val albumId: Int,
    val albumArtistId: Int,
    val albumName: String,
    val albumRealiseDate: String,
    val albumTrackCount: Int,
    val albumArtWorkUrl: String,
    val albumArtistName: String,
    val albumPrice: Double)