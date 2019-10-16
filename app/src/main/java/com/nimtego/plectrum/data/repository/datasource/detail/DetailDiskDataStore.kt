package com.nimtego.plectrum.data.repository.datasource.detail

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import io.reactivex.Observable
import javax.inject.Inject

class DetailDiskDataStore @Inject constructor(
//        private val cacheSong: Cache<String, SongResult>,
//        private val cacheAlbum: Cache<String, AlbumResult>,
//        private val cacheArtist: Cache<String, ArtistResult>
) : DetailMusicalDataStore {

    override fun songById(id: String): Observable<List<SongResult>> {
        return Observable.empty()
    }

    override fun albumById(id: String): Observable<AlbumResult> {
        return Observable.empty()
    }

    override fun artistById(id: String): Observable<ArtistResult> {
        return Observable.empty()
    }

    override fun songsByAlbumId(id: String): Observable<List<SongResult>> {
        return Observable.empty()
    }

    override fun albumsByArtistId(id: String): Observable<List<AlbumResult>> {
        return Observable.empty()
    }
}