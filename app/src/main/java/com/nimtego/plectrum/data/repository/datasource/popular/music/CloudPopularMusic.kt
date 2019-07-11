package com.nimtego.plectrum.data.repository.datasource.popular.music

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieKey
import io.reactivex.Observable
import javax.inject.Inject

class CloudPopularMusic @Inject constructor (
        private val rssItunesApi: RssItunesApi,
        private val cache: Cache<String, PopularResponse>
) : PopularMusicDataStore {

    override fun hotTrack(): Observable<PopularResponse> {
        return rssItunesApi.hotTrack().doOnNext {
            this@CloudPopularMovie.cache.put(PopularMovieKey.HOT_TRACK, it)
        }
    }

    override fun newTrack(): Observable<PopularResponse> {
        return rssItunesApi.newMusic().doOnNext {
            this@CloudPopularMovie.cache.put(PopularMovieKey.NEW_TRACK, it)
        }
    }

    override fun topTrack(): Observable<PopularResponse> {
        return rssItunesApi.topSong().doOnNext {
            this@CloudPopularMovie.cache.put(PopularMovieKey.TOP_TRACK, it)
        }
    }

    override fun topAlbum(): Observable<PopularResponse> {
        return rssItunesApi.topAlbums().doOnNext {
            this@CloudPopularMovie.cache.put(PopularMovieKey.TOP_TRACK, it)
        }
    }
}