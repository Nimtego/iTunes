package com.nimtego.plectrum.data.repository.datasource.detail

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.data.network.itunes.ITunesApi
import com.nimtego.plectrum.data.network.itunes.ItunesFabricParam
import io.reactivex.Observable
import javax.inject.Inject

class DetailCloudDataStore @Inject constructor(
        private val api: ITunesApi
//        private val cacheSong: Cache<String, SongResult>,
//        private val cacheAlbum: Cache<String, AlbumResult>,
//        private val cacheArtist: Cache<String, ArtistResult>
) : DetailMusicalDataStore {

    override fun songById(id: String): Observable<List<SongResult>> {
        return this.api.getSongs(ItunesFabricParam.lookupSongsById(id))
                .map { it.results }
    }

    override fun albumById(id: String): Observable<AlbumResult> {
        return this.api.getAlbum(ItunesFabricParam.lookupAlbumById(id))
                .map {
                    it.results.first()
                }
        }

    override fun artistById(id: String): Observable<ArtistResult> {
        return this.api.getArtist(ItunesFabricParam.lookupArtist(id)).map {
            it.results.first()
        }
    }

    override fun songsByAlbumId(id: String): Observable<List<SongResult>> {
        return this.api.getSongs(ItunesFabricParam.lookupSongsById(id)).map { it.results }
    }

    override fun albumsByArtistId(id: String): Observable<List<AlbumResult>> {
        return this.api.getAlbum(ItunesFabricParam.lookupAlbumById(id)).map { it.results }
    }
}