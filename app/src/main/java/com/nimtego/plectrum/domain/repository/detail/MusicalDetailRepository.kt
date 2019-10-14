package com.nimtego.plectrum.domain.repository.detail

import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongModel
import io.reactivex.Single

interface MusicalDetailRepository {
    fun getSongById(id: String): Single<SongModel>
    fun getAlbumById(id: String): Single<AlbumModel>
    fun getArtistById(id: String): Single<ArtistModel>
}