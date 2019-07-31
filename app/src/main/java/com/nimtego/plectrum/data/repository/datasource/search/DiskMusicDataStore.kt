package com.nimtego.plectrum.data.repository.datasource.search

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.domain.repository.MusicalSource
import io.reactivex.Observable
import javax.inject.Inject

class DiskMusicDataStore @Inject constructor(
        private val cache: Cache<Int, ArtistResult>
) : MusicalSource {

    override fun getAuthorByRequest(request: String): Observable<List<ArtistResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorByAlbumId(id: Int): Observable<ArtistResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorBySongId(id: Int): Observable<ArtistResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSongsByRequest(request: String): Observable<List<SongResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSongsByAlbumId(id: Int): Observable<List<SongResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSongById(id: Int): Observable<SongResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumsByRequest(request: String): Observable<List<AlbumResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumsByAuthorId(id: Int): Observable<List<AlbumResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumBySongId(id: Int): Observable<AlbumResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumById(id: Int): Observable<AlbumResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}