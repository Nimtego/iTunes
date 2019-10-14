package com.nimtego.plectrum.data.repository.datasource.detail

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.data.network.itunes.ITunesApi
import com.nimtego.plectrum.data.network.itunes.ItunesFabricParam
import io.reactivex.Single
import javax.inject.Inject

class DetailCloudDataStore @Inject constructor(
        private val api: ITunesApi
//        private val cacheSong: Cache<String, SongResult>,
//        private val cacheAlbum: Cache<String, AlbumResult>,
//        private val cacheArtist: Cache<String, ArtistResult>
) : DetailMusicalDataStore {

    override fun songById(id: String): Single<SongResult> {
        return Single.fromObservable {
            this.api.getSongs(ItunesFabricParam.lookupSongsById(id))
//                    .doOnNext {
//                        it.results.map { result ->
//                           // cacheSong.put(result.trackId.toString(), result)
//                        }
//                    }
        }
    }

    override fun albumById(id: String): Single<AlbumResult> {
        return Single.fromObservable {
            this.api.getAlbum(ItunesFabricParam.lookupAlbumById(id))
                    .doOnNext {
                        it.results.map { result ->
                           // cacheAlbum.put(result.collectionId.toString(), result)
                        }
                    }
        }
    }

    override fun artistById(id: String): Single<ArtistResult> {
        return Single.fromObservable {
            this.api.getArtist(ItunesFabricParam.lookupArtist(id))
                    .doOnNext {
                        it.results.map { result ->
                          //  cacheArtist.put(result.artistId.toString(), result)
                        }
                    }
        }
    }

    override fun songsByAlbumId(id: String): Single<List<SongResult>> {
        return Single.fromObservable {
            this.api.getSongs(ItunesFabricParam.lookupSongsById(id))
                    .doOnNext {
                        it.results.map { result ->
                            //cacheSong.put(result.trackId.toString(), result)
                        }
                    }
        }
    }

    override fun albumsByArtistId(id: String): Single<List<AlbumResult>> {
        return Single.fromObservable {
            this.api.getAlbum(ItunesFabricParam.lookupAlbumById(id))
                    .doOnNext {
                        it.results.map { result ->
                           // cacheAlbum.put(result.collectionId.toString(), result)
                        }
                    }
        }
    }
}