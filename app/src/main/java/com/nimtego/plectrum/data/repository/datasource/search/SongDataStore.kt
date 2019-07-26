package com.nimtego.plectrum.data.repository.datasource.search

import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.domain.repository.SongSource
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import io.reactivex.Observable

class SongDataStore : SongSource<SongResult> {

    override fun getSongsByRequest(request: String): Observable<List<SongResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSongsByAlbumId(id: Int): Observable<List<SongResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSongById(id: Int): Observable<SongResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
