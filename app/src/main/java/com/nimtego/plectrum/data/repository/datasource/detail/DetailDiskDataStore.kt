package com.nimtego.plectrum.data.repository.datasource.detail

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import io.reactivex.Single
import javax.inject.Inject

class DetailDiskDataStore @Inject constructor(
//        private val cacheSong: Cache<String, SongResult>,
//        private val cacheAlbum: Cache<String, AlbumResult>,
//        private val cacheArtist: Cache<String, ArtistResult>
) : DetailMusicalDataStore {

    override fun songById(id: String): Single<SongResult> {
        return Single.fromObservable {
            //cacheSong.get(id)
        }
    }

    override fun albumById(id: String): Single<AlbumResult> {
        return Single.fromObservable {
            //cacheAlbum.get(id)
        }
    }

    override fun artistById(id: String): Single<ArtistResult> {
        return Single.fromObservable {
            //cacheArtist.get(id)
        }
    }

    override fun songsByAlbumId(id: String): Single<List<SongResult>> {
        return Single.fromObservable {
            //cacheSong.getAllValue().map {
               // it.filter { songResult -> songResult.collectionId.toString() == id }
           // }
        }
    }

    override fun albumsByArtistId(id: String): Single<List<AlbumResult>> {
        return Single.fromObservable {
           // cacheAlbum.getAllValue().map {
             //   it.filter { albumResult -> albumResult.artistId.toString() == id }
           // }
        }
    }
}