package com.nimtego.plectrum.data.repository.datasource.popular.music

import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesFabricParam
import io.reactivex.Observable
import javax.inject.Inject

class CloudPopularMusic @Inject constructor (
        private val rssItunesApi: RssItunesApi,
        private val cache: PopularResponseCache
) : PopularMusicDataStore {

    override fun hotTrack(responseSize: Int): Observable<PopularResponse> {
        return rssItunesApi.getPopularContent(RssItunesFabricParam.hotTracks(responseSize))
                .doOnNext {
                    this@CloudPopularMusic.cache.put(PopularMusicKey.HOT_TRACK.key, it)
                }
    }

    override fun newTrack(responseSize: Int): Observable<PopularResponse> {
        return rssItunesApi.getPopularContent(RssItunesFabricParam.newMusic(responseSize))
                .doOnNext {
                    this@CloudPopularMusic.cache.put(PopularMusicKey.NEW_TRACK.key, it)
                }
    }

    override fun topTrack(responseSize: Int): Observable<PopularResponse> {
        return rssItunesApi.getPopularContent(RssItunesFabricParam.topSongs(responseSize))
                .doOnNext {
                    this@CloudPopularMusic.cache.put(PopularMusicKey.TOP_TRACK.key, it)
                }
    }

    override fun topAlbum(responseSize: Int): Observable<PopularResponse> {
        return rssItunesApi.getPopularContent(RssItunesFabricParam.topAlbum(responseSize))
                .doOnNext {
                    this@CloudPopularMusic.cache.put(PopularMusicKey.TOP_TRACK.key, it)
                }
    }
}