package com.nimtego.plectrum.presentation.mvp.model.movie

data class Movie(
        val movieDirectorName: String,
        val movieId: Int,
        val movieName: String,
        val movieTimeMills: Int,
        val movieLongDescription: String,
        val moviePrimaryGenreName: String,
        val movieRealiseDate: String,
        val moviePreviewUrl: String,
        val movieArtWorkUrl: String,
        val moviePrice: Double)