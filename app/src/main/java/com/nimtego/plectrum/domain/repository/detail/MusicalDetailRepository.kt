package com.nimtego.plectrum.domain.repository.detail

import com.nimtego.plectrum.presentation.mvp.model.music.AlbumDetailModel
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistDetailModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongDetailModel
import io.reactivex.Observable

interface MusicalDetailRepository {
    fun getSongById(id: String): Observable<SongDetailModel>
    fun getAlbumById(id: String): Observable<AlbumDetailModel>
    fun getArtistById(id: String): Observable<ArtistDetailModel>
}