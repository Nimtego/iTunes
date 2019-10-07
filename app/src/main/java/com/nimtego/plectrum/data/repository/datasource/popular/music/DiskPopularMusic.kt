package com.nimtego.plectrum.data.repository.datasource.popular.music

import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable
import javax.inject.Inject

class DiskPopularMusic @Inject constructor(
        private val cache: PopularResponseCache
) : PopularMusicDataStore {

    override fun hotTrack(responseSize: Int): Observable<PopularResponse> {
        return cache.get(PopularMusicKey.HOT_TRACK.key)
    }

    override fun newTrack(responseSize: Int): Observable<PopularResponse> {
        return cache.get(PopularMusicKey.NEW_TRACK.key)
    }

    override fun topTrack(responseSize: Int): Observable<PopularResponse> {
        return cache.get(PopularMusicKey.TOP_TRACK.key)
    }

    override fun topAlbum(responseSize: Int): Observable<PopularResponse> {
        return cache.get(PopularMusicKey.TOP_ALBUM.key)
    }
}