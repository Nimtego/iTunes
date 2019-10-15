package com.nimtego.plectrum.domain.repository.detail

import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongModel
import io.reactivex.Observable

interface MusicalDetailRepository {
    fun getSongById(id: String): Observable<SongModel>
    fun getAlbumById(id: String): Observable<AlbumModel>
    fun getArtistById(id: String): Observable<ArtistModel>
}