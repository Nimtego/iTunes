package com.nimtego.plectrum.data.repository.datasource.search

import com.nimtego.plectrum.domain.repository.AlbumSource
import com.nimtego.plectrum.presentation.mvp.model.song.Album
import io.reactivex.Observable

class AlbumDataStore : AlbumSource {
    override fun getAlbumsByRequest(request: String): Observable<List<Album>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumsByAuthorId(id: Int): Observable<List<Album>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumBySongId(id: Int): Observable<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumById(id: Int): Observable<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
