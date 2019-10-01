package com.nimtego.plectrum.data.repository.datasource.popular.book

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable
import javax.inject.Inject

class PopularBookFactory @Inject constructor(
        private val cache: PopularResponseCache,
        private val cloudDataStore: CloudPopularBook,
        private val diskPopularBook: DiskPopularBook
) : PopularBookDataStore {

    override fun topFreeBook(responseSize: Int): Observable<PopularResponse> {
        return Observable.concat(diskPopularBook.topFreeBook(responseSize),
                cloudDataStore.topFreeBook(responseSize))
    }

    override fun topPaidBook(responseSize: Int): Observable<PopularResponse> {
        return Observable.concat(diskPopularBook.topPaidBook(responseSize),
                cloudDataStore.topPaidBook(responseSize))
    }
}