package com.nimtego.plectrum.data.repository.datasource.popular.movie

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable

interface PopularMovieDataStore {
    fun topMovie(): Observable<PopularResponse>
}