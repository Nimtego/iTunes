package com.nimtego.plectrum.domain.repository

import com.nimtego.plectrum.presentation.mvp.model.song.Author
import io.reactivex.Observable

interface AuthorSource<R> {
    fun getAuthorByRequest(request: String): Observable<List<R>>
    fun getAuthorByAlbumId(id: Int): Observable<R>
    fun getAuthorBySongId(id: Int): Observable<R>

}