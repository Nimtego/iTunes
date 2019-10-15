package com.nimtego.plectrum.data.repository.datasource.detail

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import io.reactivex.Observable

interface DetailMusicalDataStore {
    fun songById(id: String): Observable<SongResult>
    fun albumById(id: String): Observable<AlbumResult>
    fun artistById(id: String): Observable<ArtistResult>
    fun songsByAlbumId(id: String): Observable<List<SongResult>>
    fun albumsByArtistId(id: String): Observable<List<AlbumResult>>
}