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

    override fun topFreeBook(): Observable<PopularResponse> {
        return if (cache.isCached(PopularBookKey.TOP_FREE_BOOK)) {
            diskPopularBook.topFreeBook()
        } else {
            cloudDataStore.topFreeBook()
        }
    }

    override fun topPaidBook(): Observable<PopularResponse> {
        return if (cache.isCached(PopularBookKey.TOP_PAID_BOOK)) {
            diskPopularBook.topPaidBook()
        } else {
            cloudDataStore.topPaidBook()
        }
    }
}