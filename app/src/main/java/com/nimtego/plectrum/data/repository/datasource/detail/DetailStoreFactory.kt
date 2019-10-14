package com.nimtego.plectrum.data.repository.datasource.detail

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import io.reactivex.Single
import javax.inject.Inject

class DetailStoreFactory @Inject constructor(
        private val cloudDataStore: DetailMusicalDataStore,
        private val diskPopularBook: DetailMusicalDataStore
) : DetailMusicalDataStore {

    override fun songById(id: String): Single<SongResult> {
//        return Single.concat(diskPopularBook.songById(id),
//                cloudDataStore.songById(id)
//        ).firstElement().toSingle()
        return cloudDataStore.songById(id)
    }

    override fun albumById(id: String): Single<AlbumResult> {
        return Single.concat(diskPopularBook.albumById(id),
                cloudDataStore.albumById(id)
        ).firstElement().toSingle()
    }

    override fun artistById(id: String): Single<ArtistResult> {
        return Single.concat(diskPopularBook.artistById(id),
                cloudDataStore.artistById(id)
        ).firstElement().toSingle()
    }

    override fun songsByAlbumId(id: String): Single<List<SongResult>> {
        return Single.concat(diskPopularBook.songsByAlbumId(id),
                cloudDataStore.songsByAlbumId(id)
        ).firstElement().toSingle()
    }

    override fun albumsByArtistId(id: String): Single<List<AlbumResult>> {
        return Single.concat(diskPopularBook.albumsByArtistId(id),
                cloudDataStore.albumsByArtistId(id)
        ).firstElement().toSingle()
    }
}