package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.domain.repository.MovieSource
import com.nimtego.plectrum.presentation.interactor.MovieSearchUseCase
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.mvp.model.movie.Movie
import io.reactivex.Observable
import javax.inject.Inject

class SearchMovieContentInteractor @Inject constructor (
        private val schedulersProvider: SchedulersProvider,
        private val movieRepository: MovieSource<Movie>
) : MovieSearchUseCase {

    override fun searchMovie(request: String): Observable<List<Movie>> {
        return this.movieRepository.getMovieByRequest(request)
                .subscribeOn(schedulersProvider.io())
    }


    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }

        }
    }

}