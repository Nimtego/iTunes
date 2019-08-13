package com.nimtego.plectrum.presentation.interactor

import com.nimtego.plectrum.presentation.mvp.model.movie.Movie
import io.reactivex.Observable

interface MovieSearchUseCase {
    fun searchMovie(request: String): Observable<List<Movie>>
}