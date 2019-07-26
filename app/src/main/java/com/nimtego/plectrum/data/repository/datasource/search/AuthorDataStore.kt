package com.nimtego.plectrum.data.repository.datasource.search

import com.nimtego.plectrum.domain.repository.AuthorSource
import com.nimtego.plectrum.presentation.mvp.model.song.Author
import io.reactivex.Observable

class AuthorDataStore : AuthorSource {
    override fun getAuthorByRequest(request: String): Observable<List<Author>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorByAlbumId(id: Int): Observable<Author> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorBySongId(id: Int): Observable<Author> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
