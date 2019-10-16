package com.nimtego.plectrum.presentation.interactor

import com.nimtego.plectrum.presentation.mvp.model.music.AlbumDetailModel
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistDetailModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongDetailModel
import io.reactivex.Single

interface MusicalInformationUseCase {
    fun song(id: String): Single<SongDetailModel>
    fun album(id: String): Single<AlbumDetailModel>
    fun artist(id: String): Single<ArtistDetailModel>
}