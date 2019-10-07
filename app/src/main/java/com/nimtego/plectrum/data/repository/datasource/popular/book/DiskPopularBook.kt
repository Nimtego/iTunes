package com.nimtego.plectrum.data.repository.datasource.popular.book

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieKey
import io.reactivex.Observable
import javax.inject.Inject

class DiskPopularBook @Inject constructor(
        private val cache: PopularResponseCache
) : PopularBookDataStore {

    override fun topFreeBook(responseSize: Int): Observable<PopularResponse> {
        return cache.get(PopularBookKey.TOP_FREE_BOOK.key)
    }

    override fun topPaidBook(responseSize: Int): Observable<PopularResponse> {
        return cache.get(PopularBookKey.TOP_PAID_BOOK.key)
    }
}