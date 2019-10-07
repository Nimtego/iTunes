package com.nimtego.plectrum.data.repository.datasource.popular.music

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable

interface PopularMusicDataStore {
    fun hotTrack(responseSize: Int): Observable<PopularResponse>
    fun newTrack(responseSize: Int): Observable<PopularResponse>
    fun topTrack(responseSize: Int): Observable<PopularResponse>
    fun topAlbum(responseSize: Int): Observable<PopularResponse>
}