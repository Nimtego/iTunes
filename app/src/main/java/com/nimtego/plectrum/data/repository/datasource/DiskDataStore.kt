package com.nimtego.plectrum.data.repository.datasource


import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.model.itunes.AlbumsRepository
import com.nimtego.plectrum.data.model.itunes.ArtistsRepository
import com.nimtego.plectrum.data.model.itunes.SongsRepository
import com.nimtego.plectrum.data.model.wiki.WikiSearchResult

import io.reactivex.Observable

class DiskDataStore<E>(private val cache: Cache<E>) : DataStore {


    override fun topMovie(): Observable<PopularResponse> {
        //todo
        return Observable.create { PopularResponse() }
    }

    override fun wikiSearch(response: String): Observable<WikiSearchResult> {
        //todo
        return Observable.create { WikiSearchResult() }
    }

    override fun songs(response: String): Observable<SongsRepository> {
        //todo
        return Observable.create { SongsRepository() }
    }

    override fun songsByIdAlbum(id: Int): Observable<SongsRepository> {
        //todo
        return Observable.create { SongsRepository() }
    }

    override fun artists(response: String): Observable<ArtistsRepository> {
        //todo
        return Observable.create { ArtistsRepository() }
    }


    override fun albums(response: String): Observable<AlbumsRepository> {
        //todo
        return Observable.create { AlbumsRepository() }
    }

    override fun songById(id: Int): Observable<SongsRepository> {
        //todo
        return Observable.create { SongsRepository() }
    }

    override fun artistById(id: Int): Observable<ArtistsRepository> {
        //todo
        return Observable.create { ArtistsRepository() }
    }


    override fun album(response: String): Observable<AlbumsRepository> {
        //todo
        return Observable.create { AlbumsRepository() }
    }

    override fun recent(): Observable<PopularResponse> {
        //todo
        return Observable.create { PopularResponse() }
    }

    override fun topSong(size: Int): Observable<PopularResponse> {
        //todo
        return Observable.create { PopularResponse() }
    }

    override fun topAlbum(): Observable<PopularResponse> {
        //todo
        return Observable.create { PopularResponse() }
    }

    override fun hot(): Observable<PopularResponse> {
        //todo
        return Observable.create { PopularResponse() }
    }

    override fun newMusic(): Observable<PopularResponse> {
        //todo
        return Observable.create { PopularResponse() }
    }

    override fun topFreeBooks(): Observable<PopularResponse> {
        //todo
        return Observable.create { PopularResponse() }
    }

    override fun topPaidBooks(): Observable<PopularResponse> {
        //todo
        return Observable.create { PopularResponse() }
    }

}
