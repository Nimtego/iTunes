package com.nimtego.plectrum.presentation.interactor.detail

import com.nimtego.plectrum.presentation.mvp.model.music.SongModel
import io.reactivex.Single

interface SongDetailUseCase {
    fun songModelById(id: String): Single<SongModel>
}