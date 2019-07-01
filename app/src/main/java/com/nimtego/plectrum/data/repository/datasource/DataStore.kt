package com.nimtego.plectrum.data.repository.datasource

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.model.itunes.AlbumsRepository
import com.nimtego.plectrum.data.model.itunes.ArtistsRepository
import com.nimtego.plectrum.data.model.itunes.SongsRepository
import com.nimtego.plectrum.data.model.wiki.WikiSearchResult

import io.reactivex.Observable

@Deprecated("<ISP. Remove when stops being useful>")
interface DataStore {

    fun wikiSearch(response: String): Observable<WikiSearchResult>

    fun songs(response: String): Observable<SongsRepository>

    fun songsByIdAlbum(id: Int): Observable<SongsRepository>

    fun artists(response: String): Observable<ArtistsRepository>

    fun albums(response: String): Observable<AlbumsRepository>

    fun songById(id: Int): Observable<SongsRepository>

    fun artistById(id: Int): Observable<ArtistsRepository>

    fun album(response: String): Observable<AlbumsRepository>

    fun recent(): Observable<PopularResponse>

    fun topSong(size: Int): Observable<PopularResponse>

    fun topAlbum(): Observable<PopularResponse>

    fun hot(): Observable<PopularResponse>

    fun newMusic(): Observable<PopularResponse>

    fun topMovie(): Observable<PopularResponse>

    fun topFreeBooks(): Observable<PopularResponse>

    fun topPaidBooks(): Observable<PopularResponse>
}
