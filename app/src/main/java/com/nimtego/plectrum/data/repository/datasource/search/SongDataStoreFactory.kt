package com.nimtego.plectrum.data.repository.datasource.search

import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.data.repository.datasource.popular.music.CloudPopularMusic
import com.nimtego.plectrum.data.repository.datasource.popular.music.DiskPopularMusic
import com.nimtego.plectrum.domain.repository.SongSource
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import io.reactivex.Observable
import javax.inject.Inject

class SongDataStoreFactory @Inject constructor(
        //private val cache: PopularResponseCache,
        private val cloudDataStore: CloudMusicDataStore
        //private val diskDataStore: DiskMusicDataStore
) : SongSource<SongResult> {

    override fun getSongsByRequest(request: String): Observable<List<SongResult>> {
        return cloudDataStore.getSongsByRequest(request)
    }

    override fun getSongsByAlbumId(id: Int): Observable<List<SongResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSongById(id: Int): Observable<SongResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
