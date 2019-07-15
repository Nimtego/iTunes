package com.nimtego.plectrum.data.repository.datasource.popular.music

import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import io.reactivex.Observable
import javax.inject.Inject

class CloudPopularMusic @Inject constructor (
        private val rssItunesApi: RssItunesApi,
        private val cache: PopularResponseCache
) : PopularMusicDataStore {

    override fun hotTrack(): Observable<PopularResponse> {
        return rssItunesApi.hotTrack().doOnNext {
            this@CloudPopularMusic.cache.put(PopularMusicKey.HOT_TRACK, it)
        }
    }

    override fun newTrack(): Observable<PopularResponse> {
        return rssItunesApi.newMusic().doOnNext {
            this@CloudPopularMusic.cache.put(PopularMusicKey.NEW_TRACK, it)
        }
    }

    override fun topTrack(): Observable<PopularResponse> {
        return rssItunesApi.topSong().doOnNext {
            this@CloudPopularMusic.cache.put(PopularMusicKey.TOP_TRACK, it)
        }
    }

    override fun topAlbum(): Observable<PopularResponse> {
        return rssItunesApi.topAlbums().doOnNext {
            this@CloudPopularMusic.cache.put(PopularMusicKey.TOP_TRACK, it)
        }
    }
}