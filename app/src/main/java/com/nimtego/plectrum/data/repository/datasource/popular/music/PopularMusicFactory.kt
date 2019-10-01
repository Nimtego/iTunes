package com.nimtego.plectrum.data.repository.datasource.popular.music

import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable
import javax.inject.Inject

class PopularMusicFactory @Inject constructor(
        private val cache: PopularResponseCache,
        private val cloudDataStore: CloudPopularMusic,
        private val diskPopularMusic: DiskPopularMusic
) : PopularMusicDataStore {

    override fun hotTrack(responseSize: Int): Observable<PopularResponse> {
        return Observable.concat(diskPopularMusic.hotTrack(responseSize),
                                 cloudDataStore.hotTrack(responseSize))
    }

    override fun newTrack(responseSize: Int): Observable<PopularResponse> {
        return Observable.concat(diskPopularMusic.newTrack(responseSize),
                                 cloudDataStore.newTrack(responseSize))
    }

    override fun topTrack(responseSize: Int): Observable<PopularResponse> {
        return Observable.concat(diskPopularMusic.topTrack(responseSize),
                                 cloudDataStore.topTrack(responseSize))
    }

    override fun topAlbum(responseSize: Int): Observable<PopularResponse> {
        return Observable.concat(diskPopularMusic.topAlbum(responseSize),
                cloudDataStore.topAlbum(responseSize))
    }
}