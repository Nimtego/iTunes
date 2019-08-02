package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.mappers.MusicalContentMapper
import com.nimtego.plectrum.domain.repository.AlbumSource
import com.nimtego.plectrum.presentation.mvp.model.song.Album
import io.reactivex.Observable

class AlbumRepository(
        private val dataStoreFactory: AlbumSource<AlbumResult>,
        private val mapper: MusicalContentMapper
) : AlbumSource<Album> {

    override fun getAlbumsByAuthorId(id: Int): Observable<List<Album>> {
        return dataStoreFactory.getAlbumsByAuthorId(id).map {
            it.map { result ->
                mapper.albumResultToAlbum(result)
            }
        }
    }

    override fun getAlbumBySongId(id: Int): Observable<Album> {
        return dataStoreFactory.getAlbumBySongId(id).map {
            mapper.albumResultToAlbum(it)
        }
    }

    override fun getAlbumById(id: Int): Observable<Album> {
        return dataStoreFactory.getAlbumById(id).map {
            mapper.albumResultToAlbum(it)
        }
    }

    override fun getAlbumsByRequest(request: String): Observable<List<Album>> {
        return dataStoreFactory.getAlbumsByRequest(request).map {
            it.map { result ->
                mapper.albumResultToAlbum(result)
            }
        }
    }
}