package com.nimtego.plectrum.data.repository.datasource.rss_itunes

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.model.itunes.AlbumsRepository
import com.nimtego.plectrum.data.model.itunes.ArtistsRepository
import com.nimtego.plectrum.data.model.itunes.SongsRepository
import com.nimtego.plectrum.data.model.wiki.WikiSearchResult

import io.reactivex.Observable

interface RssItunesDataStore {

    fun recentSong(): Observable<PopularResponse>

    fun topSong(size: Int): Observable<PopularResponse>

    fun topAlbum(): Observable<PopularResponse>

    fun hotSong(): Observable<PopularResponse>

    fun newSong(): Observable<PopularResponse>

    fun topMovie(): Observable<PopularResponse>
}
