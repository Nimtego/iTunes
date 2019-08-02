package com.nimtego.plectrum.domain.repository

import com.nimtego.plectrum.presentation.mvp.model.movie.Movie
import io.reactivex.Observable

interface VideoSource {

    fun getMovieByRequest(request: String): Observable<List<Movie>>
    fun getMovieById(id: Int): Observable<Movie>
}