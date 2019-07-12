package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.presentation.mvp.model.song.Album
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import io.reactivex.Observable

interface PopularMusic {
    fun hotTrack(): Observable<List<Song>>
    fun newTrack(): Observable<List<Song>>
    fun topTrack(): Observable<List<Song>>
    fun newAlbum(): Observable<List<Album>>
}