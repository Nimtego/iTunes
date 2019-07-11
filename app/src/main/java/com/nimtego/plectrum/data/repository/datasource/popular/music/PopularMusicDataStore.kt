package com.nimtego.plectrum.data.repository.datasource.popular.music

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable

interface PopularMusicDataStore {
    fun hotTrack(): Observable<PopularResponse>
    fun newTrack(): Observable<PopularResponse>
    fun topTrack(): Observable<PopularResponse>
    fun topAlbum(): Observable<PopularResponse>
}