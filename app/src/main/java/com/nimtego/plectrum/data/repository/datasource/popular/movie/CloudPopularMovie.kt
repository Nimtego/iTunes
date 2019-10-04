package com.nimtego.plectrum.data.repository.datasource.popular.movie

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesFabricParam
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieKey
import io.reactivex.Observable
import javax.inject.Inject

class CloudPopularMovie @Inject constructor (
        private val rssItunesApi: RssItunesApi,
        private val cache: PopularResponseCache
) : PopularMovieDataStore {

    override fun topMovie(responseSize: Int): Observable<PopularResponse> {
        return rssItunesApi.getPopularContent(RssItunesFabricParam.topMovies(responseSize))
                .doOnNext {
                    this@CloudPopularMovie.cache.put(PopularMovieKey.TOP_MOVIE.key, it)
                }
    }
}