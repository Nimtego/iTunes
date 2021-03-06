package com.nimtego.plectrum.domain.repository

import io.reactivex.Observable

interface SongSource<R> {
    fun getSongsByRequest(request: String): Observable<List<R>>
    fun getSongsByAlbumId(id: Int): Observable<List<R>>
    fun getSongById(id: Int): Observable<R>
}