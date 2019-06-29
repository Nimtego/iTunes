package com.nimtego.plectrum.data.repository.datasource.dash_board

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable

interface SongRecentDataStore {

    fun recent(): Observable<PopularResponse>

    fun topSong(): Observable<PopularResponse>

    fun topAlbum(): Observable<PopularResponse>

    fun hot(): Observable<PopularResponse>

    fun newMusick(): Observable<PopularResponse>
}