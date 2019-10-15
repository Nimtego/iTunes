package com.nimtego.plectrum.presentation.interactor.detail

import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import io.reactivex.Observable

interface AlbumDetailUseCase {
    fun albumModelById(id: String): Observable<AlbumModel>
}