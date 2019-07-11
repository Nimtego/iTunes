package com.nimtego.plectrum.data.repository.datasource.popular.movie

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieKey
import io.reactivex.Observable
import javax.inject.Inject

class CloudPopularMovie @Inject constructor (
        private val rssItunesApi: RssItunesApi,
        private val cache: Cache<String, PopularResponse>
) : PopularMovieDataStore {

    override fun topMovie(): Observable<PopularResponse> {
        return rssItunesApi.hotTrack().doOnNext {
            this@CloudPopularMovie.cache.put(PopularMovieKey.TOP_MOVIE, it)
        }
    }
}