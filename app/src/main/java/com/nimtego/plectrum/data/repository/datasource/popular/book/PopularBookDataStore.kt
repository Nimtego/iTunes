package com.nimtego.plectrum.data.repository.datasource.popular.book

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable

interface PopularBookDataStore {
    fun topFreeBook(): Observable<PopularResponse>
    fun topPaidBook(): Observable<PopularResponse>
}