package com.nimtego.plectrum.data.repository.datasource.popular.book

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieKey
import io.reactivex.Observable
import javax.inject.Inject

class CloudPopularBook @Inject constructor (
        private val rssItunesApi: RssItunesApi,
        private val cache: PopularResponseCache
) : PopularBookDataStore {

    override fun topFreeBook(): Observable<PopularResponse> {
        return rssItunesApi.topFreeBooks().doOnNext {
            this@CloudPopularBook.cache.put(PopularBookKey.TOP_FREE_BOOK, it)
        }
    }

    override fun topPaidBook(): Observable<PopularResponse> {
        return rssItunesApi.topPaidBooks().doOnNext {
            this@CloudPopularBook.cache.put(PopularBookKey.TOP_PAID_BOOK, it)
        }
    }
}