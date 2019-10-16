package com.nimtego.plectrum.presentation.interactor.detail

import com.nimtego.plectrum.presentation.mvp.model.music.SongDetailModel
import io.reactivex.Observable

interface SongDetailUseCase {
    fun songModelById(id: String): Observable<SongDetailModel>
}