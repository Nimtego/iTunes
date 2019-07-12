package com.nimtego.plectrum.presentation.mvp.model.song

import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel

data class Album(
    val albumId: Int,
    val albumArtistId: Int,
    val albumName: String,
    val albumRealiseDate: String,
    val albumTrackCount: Int,
    val albumArtWorkUrl: String,
    val albumArtistName: String,
    val albumPrice: Double)