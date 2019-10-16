package com.nimtego.plectrum.data.repository.datasource.detail

import com.nimtego.plectrum.data.model.itunes.AlbumResult
import com.nimtego.plectrum.data.model.itunes.ArtistResult
import com.nimtego.plectrum.data.model.itunes.SongResult
import io.reactivex.Observable
import javax.inject.Inject

class DetailStoreFactory @Inject constructor(
        private val cloudDataStore: DetailMusicalDataStore,
        private val diskPopularBook: DetailMusicalDataStore
) : DetailMusicalDataStore {

    override fun songById(id: String): Observable<List<SongResult>> {
        return Observable.concat(diskPopularBook.songById(id),
                cloudDataStore.songById(id)
        ).firstElement().toObservable()
    }

    override fun albumById(id: String): Observable<AlbumResult> {
        return Observable.concat(diskPopularBook.albumById(id),
                cloudDataStore.albumById(id)
        ).firstElement().toObservable()
    }

    override fun artistById(id: String): Observable<ArtistResult> {
        return Observable.concat(diskPopularBook.artistById(id),
                cloudDataStore.artistById(id)
        ).firstElement().toObservable()
    }

    override fun songsByAlbumId(id: String): Observable<List<SongResult>> {
        return Observable.concat(diskPopularBook.songsByAlbumId(id),
                cloudDataStore.songsByAlbumId(id)
        ).firstElement().toObservable()
    }

    override fun albumsByArtistId(id: String): Observable<List<AlbumResult>> {
        return Observable.concat(diskPopularBook.albumsByArtistId(id),
                cloudDataStore.albumsByArtistId(id)
        ).firstElement().toObservable()
    }
}