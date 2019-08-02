package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.mappers.MusicalContentMapper
import com.nimtego.plectrum.domain.repository.AuthorSource
import com.nimtego.plectrum.presentation.mvp.model.song.Author
import io.reactivex.Observable

class AuthorRepository(
        private val dataStoreFactory: AuthorSource<ArtistResult>,
        private val mapper: MusicalContentMapper
) : AuthorSource<Author> {

    override fun getAuthorByRequest(request: String): Observable<List<Author>> {
        return dataStoreFactory.getAuthorByRequest(request).map {
            it.map { result ->
                mapper.authorResultToArtist(result)
            }
        }
    }

    override fun getAuthorByAlbumId(id: Int): Observable<Author> {
        return dataStoreFactory.getAuthorByAlbumId(id).map {
            mapper.authorResultToArtist(it)
        }
    }

    override fun getAuthorBySongId(id: Int): Observable<Author> {
        return dataStoreFactory.getAuthorBySongId(id).map {
            mapper.authorResultToArtist(it)
        }
    }
}