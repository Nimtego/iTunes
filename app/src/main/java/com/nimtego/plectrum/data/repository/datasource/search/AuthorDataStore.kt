package com.nimtego.plectrum.data.repository.datasource.search

import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.domain.repository.AuthorSource
import com.nimtego.plectrum.presentation.mvp.model.song.Author
import io.reactivex.Observable

class AuthorDataStore : AuthorSource<ArtistResult> {
    override fun getAuthorByRequest(request: String): Observable<List<ArtistResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorByAlbumId(id: Int): Observable<ArtistResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorBySongId(id: Int): Observable<ArtistResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
