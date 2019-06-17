package com.nimtego.plectrum.data.repository.datasource.dash_board

import com.nimtego.plectrum.data.cache.CacheK
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.rest.network.rss_itunes.RssItunesApi
import io.reactivex.Observable
import javax.inject.Inject

class CloudSongRecent<R : PopularResponse> @Inject constructor (
        private val rssItunesApi: RssItunesApi,
        private val cache: CacheK<R>
) : SongRecentDataStore {
    override fun recent(): Observable<PopularResponse> {
        return rssItunesApi.recent()
    }

    override fun topSong(): Observable<PopularResponse> {
       return rssItunesApi.topSong()
    }

    override fun topAlbum(): Observable<PopularResponse> {
        return rssItunesApi.topAlbums()
    }

    override fun hot(): Observable<PopularResponse> {
       return rssItunesApi.hotTrack()
    }

    override fun newMusick(): Observable<PopularResponse> {
        return rssItunesApi.newMusic()
    }
}