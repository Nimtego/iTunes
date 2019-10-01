package com.nimtego.plectrum.data.repository.datasource.popular.book

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable

interface PopularBookDataStore {
    fun topFreeBook(responseSize: Int): Observable<PopularResponse>
    fun topPaidBook(responseSize: Int): Observable<PopularResponse>
}