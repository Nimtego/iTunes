package com.nimtego.plectrum.data.repository.datasource.search

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.domain.repository.AlbumSource
import com.nimtego.plectrum.presentation.mvp.model.song.Album
import io.reactivex.Observable

class AlbumDataStore : AlbumSource<AlbumResult> {
    override fun getAlbumsByRequest(request: String): Observable<List<AlbumResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumsByAuthorId(id: Int): Observable<List<AlbumResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumBySongId(id: Int): Observable<AlbumResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumById(id: Int): Observable<AlbumResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
