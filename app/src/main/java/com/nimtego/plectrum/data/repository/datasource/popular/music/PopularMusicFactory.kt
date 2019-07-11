package com.nimtego.plectrum.data.repository.datasource.popular.music

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable
import javax.inject.Inject

class PopularMusicFactory @Inject constructor(
        private val cache: Cache<String, PopularResponse>,
        private val cloudDataStore: CloudPopularMusic,
        private val diskPopularMusic: DiskPopularMusic
) : PopularMusicDataStore {



    override fun hotTrack(): Observable<PopularResponse> {
        return if (cache.isCached(PopularMusicKey.HOT_TRACK)) {
            diskPopularMusic.hotTrack()
        } else {
            cloudDataStore.hotTrack()
        }
    }

    override fun newTrack(): Observable<PopularResponse> {
        return if (cache.isCached(PopularMusicKey.NEW_TRACK)) {
            diskPopularMusic.newTrack()
        } else {
            cloudDataStore.newTrack()
        }
    }

    override fun topTrack(): Observable<PopularResponse> {
        return if (cache.isCached(PopularMusicKey.TOP_TRACK)) {
            diskPopularMusic.topTrack()
        } else {
            cloudDataStore.topTrack()
        }
    }

    override fun topAlbum(): Observable<PopularResponse> {
        return if (cache.isCached(PopularMusicKey.TOP_ALBUM)) {
            diskPopularMusic.topAlbum()
        } else {
            cloudDataStore.topAlbum()
        }
    }
}