package com.nimtego.plectrum.presentation.interactor

import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongModel
import io.reactivex.Single

interface MusicalInformationUseCase {
    fun song(id: String): Single<SongModel>
    fun album(id: String): Single<AlbumModel>
    fun artist(id: String): Single<ArtistModel>
}