package com.nimtego.plectrum.data.model.mappers

import com.nimtego.plectrum.data.model.itunes.MovieResult
import com.nimtego.plectrum.presentation.mvp.model.movie.Movie

class MovieContentMapper {
    //todo collectionId, trackId, collectionArtistId to check in more detail who is who in postman ???
    //track’s time in milliseconds
    fun movieResultToMovie(movieResult: MovieResult): Movie {
        return Movie(movieDirectorName = movieResult.artistName,
                     movieName = movieResult.collectionName,
                     movieId = movieResult.collectionId,
                     movieRealiseDate = movieResult.releaseDate,
                     movieTimeMills = movieResult.trackTimeMillis,
                     movieArtWorkUrl = movieResult.artworkUrl100,
                     moviePreviewUrl = movieResult.previewUrl,
                     movieLongDescription = movieResult.longDescription,
                     moviePrimaryGenreName = movieResult.primaryGenreName,
                     moviePrice = movieResult.collectionPrice)
    }
}