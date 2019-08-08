package com.nimtego.plectrum.domain.repository

import io.reactivex.Observable

interface MovieSource<R> {
    fun getMovieByRequest(request: String): Observable<List<R>>
    fun getMovieById(id: Int): Observable<R>
}