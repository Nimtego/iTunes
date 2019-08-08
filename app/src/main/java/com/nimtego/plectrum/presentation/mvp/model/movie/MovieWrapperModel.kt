package com.nimtego.plectrum.presentation.mvp.model.movie

import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.song.Song

data class MovieWrapperModel (
        private val movie: PopularMovieModel
) : ChildViewModel {

    override fun mainName(): String {
        return this.movie.movieName
    }

    override fun minorName(): String {
        return this.movie.movieProducerName
    }

    override fun imageUrl(): String {
        return this.movie.movieArtWorkUrl
    }

    override fun id(): String {
        return this.movie.movieId.toString()
    }
}