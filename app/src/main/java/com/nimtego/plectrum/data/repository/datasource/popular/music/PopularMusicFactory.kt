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

    override fun hotTrack(): Observable<PopularResponse> {
        return Observable.concat(diskPopularMusic.hotTrack(),
                cloudDataStore.hotTrack())
    }

    override fun newTrack(): Observable<PopularResponse> {
        return Observable.concat(diskPopularMusic.newTrack(),
                cloudDataStore.newTrack())
    }

    override fun topTrack(): Observable<PopularResponse> {
        return Observable.concat(diskPopularMusic.topTrack(),
                cloudDataStore.topTrack())
    }

    override fun topAlbum(): Observable<PopularResponse> {
        return Observable.concat(diskPopularMusic.topAlbum(),
                cloudDataStore.topAlbum())
    }
}