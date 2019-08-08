package com.nimtego.plectrum.data.model.mappers

import com.nimtego.plectrum.data.model.rss_itunes.Result
import com.nimtego.plectrum.presentation.mvp.model.movie.Movie
import com.nimtego.plectrum.presentation.mvp.model.movie.PopularMovieModel

class PopularMovieMapper {

    fun popularResultToMovie(result: Result): PopularMovieModel {
        return PopularMovieModel(movieName = result.name,
                movieProducerName = result.artistName,
                movieId = result.id.toInt(),
                movieArtWorkUrl = result.artworkUrl100,
                movieRealiseDate = result.releaseDate)
    }

}