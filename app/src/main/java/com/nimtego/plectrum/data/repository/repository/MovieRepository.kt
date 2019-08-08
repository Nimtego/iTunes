package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.itunes.MovieResult
import com.nimtego.plectrum.data.model.mappers.MovieContentMapper
import com.nimtego.plectrum.domain.repository.MovieSource
import com.nimtego.plectrum.presentation.mvp.model.movie.Movie
import io.reactivex.Observable
import javax.inject.Inject

class MovieRepository @Inject constructor(
        private val dataStoreFactory: MovieSource<MovieResult>,
        private val mapper: MovieContentMapper
) : MovieSource<Movie> {

    override fun getMovieByRequest(request: String): Observable<List<Movie>> {
        return this.dataStoreFactory.getMovieByRequest(request).map {
            it.map { itemResult -> this.mapper.movieResultToMovie(itemResult) }
        }
    }

    override fun getMovieById(id: Int): Observable<Movie> {
        return this.dataStoreFactory.getMovieById(id).map {
            this.mapper.movieResultToMovie(it)
        }
    }
}