package com.nimtego.plectrum.domain.repository

import com.nimtego.plectrum.presentation.mvp.model.song.Album
import io.reactivex.Observable

interface AlbumSource {
    fun getAlbumsByRequest(request: String): Observable<List<Album>>
    fun getAlbumsByAuthorId(id: Int): Observable<List<Album>>
    fun getAlbumBySongId(id: Int): Observable<Album>
    fun getAlbumById(id: Int): Observable<Album>
}