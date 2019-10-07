package com.nimtego.plectrum.data.repository.datasource.popular.movie

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable
import javax.inject.Inject

class PopularMovieFactory @Inject constructor(
        private val cache: PopularResponseCache,
        private val cloudDataStore: CloudPopularMovie,
        private val diskPopularMusic: DiskPopularMovie
) : PopularMovieDataStore {

    override fun topMovie(responseSize: Int): Observable<PopularResponse> {
        return Observable.concat(diskPopularMusic.topMovie(responseSize),
                                 cloudDataStore.topMovie(responseSize))
                                 .firstElement()
                                 .toObservable()
    }
}