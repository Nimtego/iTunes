package com.nimtego.plectrum.data.repository.datasource.detail

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.domain.repository.AlbumSource
import com.nimtego.plectrum.domain.repository.AuthorSource
import io.reactivex.Single

interface DetailMusicalDataStore {
    fun songById(id: String): Single<SongResult>
    fun albumById(id: String): Single<AlbumResult>
    fun artistById(id: String): Single<ArtistResult>
    fun songsByAlbumId(id: String): Single<List<SongResult>>
    fun albumsByArtistId(id: String): Single<List<AlbumResult>>
}