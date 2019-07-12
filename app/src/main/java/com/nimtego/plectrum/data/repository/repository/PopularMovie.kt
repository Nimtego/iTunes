package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.presentation.mvp.model.movie.Movie
import io.reactivex.Observable

interface PopularMovie {
    fun hotTrack(): Observable<List<Movie>>
}