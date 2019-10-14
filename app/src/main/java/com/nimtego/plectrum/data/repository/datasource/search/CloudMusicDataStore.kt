package com.nimtego.plectrum.data.repository.datasource.search

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.data.network.itunes.ItunesFabricParam
import com.nimtego.plectrum.data.network.itunes.ITunesApi
import com.nimtego.plectrum.domain.repository.MusicalSource
import io.reactivex.Observable
import javax.inject.Inject

class CloudMusicDataStore @Inject constructor (
        private val api: ITunesApi
        //private val cache: PopularResponseCache
) : MusicalSource {

    override fun getAuthorByRequest(request: String): Observable<List<ArtistResult>> {
        return api.getArtist(ItunesFabricParam.searchArtist(request)).map { it.results }
    }

    override fun getAuthorByAlbumId(id: Int): Observable<ArtistResult> {
        return api.getArtist(ItunesFabricParam.lookupArtist(id.toString())).map { it.results.first() }
    }

    override fun getAuthorBySongId(id: Int): Observable<ArtistResult> {
        return api.getArtist(ItunesFabricParam.lookupArtist(id.toString())).map { it.results.first() }
    }

    override fun getSongsByRequest(request: String): Observable<List<SongResult>> {
        return api.searchSongs(ItunesFabricParam.searchSong(request)).map { it.results }
    }

    override fun getSongsByAlbumId(id: Int): Observable<List<SongResult>> {
        return api.getSongs(ItunesFabricParam.lookupSongsById(id.toString())).map { it.results }
    }

    override fun getSongById(id: Int): Observable<SongResult> {
        return api.getSongs(ItunesFabricParam.lookupSongsById(id.toString())).map { it.results.first() }
    }

    override fun getAlbumsByRequest(request: String): Observable<List<AlbumResult>> {
        return api.searchAlbum(ItunesFabricParam.searchAlbum(request)).map { it.results }
    }

    override fun getAlbumsByAuthorId(id: Int): Observable<List<AlbumResult>> {
        return api.getAlbum(ItunesFabricParam.lookupAlbumById(id.toString())).map { it.results }
    }

    override fun getAlbumBySongId(id: Int): Observable<AlbumResult> {
        return api.getAlbum(ItunesFabricParam.lookupAlbumById(id.toString())).map { it.results.first() }
    }

    override fun getAlbumById(id: Int): Observable<AlbumResult> {
        return api.getAlbum(ItunesFabricParam.lookupAlbumById(id.toString())).map { it.results.first() }
    }
}