package com.nimtego.plectrum.data.entity

import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.ChildViewModel

data class Song (
    val artistId: Int,
    val collectionId: Int,
    val trackId: Int,
    val artistName: String,
    val wrapperType: String,
    val trackName: String,
    val trackPrice: Double,
    val trackArtWorkUrl: String,
    val trackTimeMillis: Int) : ChildViewModel {

    override fun mainName(): String {
       return artistName
    }

    override fun minorName(): String {
        return trackName
    }

    override fun imageUrl(): String {
       return trackArtWorkUrl
    }

    override fun id(): String {
        return trackId.toString()
    }

}