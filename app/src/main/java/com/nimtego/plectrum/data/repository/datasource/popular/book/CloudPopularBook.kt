package com.nimtego.plectrum.data.repository.datasource.popular.book

import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesFabricParam
import io.reactivex.Observable
import javax.inject.Inject

class CloudPopularBook @Inject constructor (
        private val rssItunesApi: RssItunesApi,
        private val cache: PopularResponseCache
) : PopularBookDataStore {

    override fun topFreeBook(responseSize: Int): Observable<PopularResponse> {
        return rssItunesApi.getPopularContent(RssItunesFabricParam.topFreeBook(responseSize))
                .doOnNext {
                    this@CloudPopularBook.cache.put(PopularBookKey.TOP_FREE_BOOK, it)
                }
    }

    override fun topPaidBook(responseSize: Int): Observable<PopularResponse> {
        return rssItunesApi.getPopularContent(RssItunesFabricParam.topPaidBook(responseSize))
                .doOnNext {
                    this@CloudPopularBook.cache.put(PopularBookKey.TOP_PAID_BOOK, it)
                }
    }
}