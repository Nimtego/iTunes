package com.nimtego.plectrum.presentation.interactor.detail

import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import io.reactivex.Single

interface AlbumDetailUseCase {
    fun albumModelById(id: String): Single<AlbumModel>
}