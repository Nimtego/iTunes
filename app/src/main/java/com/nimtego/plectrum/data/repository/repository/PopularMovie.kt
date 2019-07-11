package com.nimtego.plectrum.data.repository.repository

import io.reactivex.Observable

interface PopularMovie {
    fun hotTrack(): Observable<List<Movie>>
}