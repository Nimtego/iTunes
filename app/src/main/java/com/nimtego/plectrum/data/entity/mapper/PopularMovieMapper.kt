package com.nimtego.plectrum.data.entity.mapper

import com.nimtego.plectrum.data.model.rss_itunes.Result
import com.nimtego.plectrum.presentation.mvp.model.movie.Movie

class PopularMovieMapper {

    fun popularResultToMovie(result: Result): Movie {
        return Movie(movieName = result.name,
                movieProducerName = result.artistName,
                movieId = result.id.toInt(),
                movieArtWorkUrl = result.artworkUrl100,
                movieRealiseDate = result.releaseDate)
    }

}