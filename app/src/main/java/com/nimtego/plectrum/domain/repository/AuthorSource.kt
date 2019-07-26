package com.nimtego.plectrum.domain.repository

import com.nimtego.plectrum.presentation.mvp.model.song.Author
import io.reactivex.Observable

interface AuthorSource {
    fun getAuthorByRequest(request: String): Observable<List<Author>>
    fun getAuthorByAlbumId(id: Int): Observable<Author>
    fun getAuthorBySongId(id: Int): Observable<Author>

}