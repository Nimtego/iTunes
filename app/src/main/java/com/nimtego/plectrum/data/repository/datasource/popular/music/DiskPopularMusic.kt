package com.nimtego.plectrum.data.repository.datasource.popular.music

import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable
import javax.inject.Inject

class DiskPopularMusic @Inject constructor(
        private val cache: PopularResponseCache
) : PopularMusicDataStore {

    override fun hotTrack(): Observable<PopularResponse> {
        return cache.get(PopularMusicKey.HOT_TRACK)
    }

    override fun newTrack(): Observable<PopularResponse> {
        return cache.get(PopularMusicKey.NEW_TRACK)
    }

    override fun topTrack(): Observable<PopularResponse> {
        return cache.get(PopularMusicKey.TOP_TRACK)
    }

    override fun topAlbum(): Observable<PopularResponse> {
        return cache.get(PopularMusicKey.TOP_ALBUM)
    }
}