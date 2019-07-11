package com.nimtego.plectrum.data.entity

import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel

data class Album(
    val albumId: Int,
    val albumArtistId: Int,
    val albumName: String,
    val albumRealiseDate: String,
    val albumTrackCount: Int,
    val albumArtWorkUrl: String,
    val albumArtistName: String,
    val albumPrice: Double) : ChildViewModel {

    override fun mainName(): String {
        return albumName
    }

    override fun minorName(): String {
        return albumArtistName
    }

    override fun imageUrl(): String {
        return albumArtWorkUrl
    }

    override fun id(): String {
        return albumId.toString()
    }

}