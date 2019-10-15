package com.nimtego.plectrum.presentation.interactor.detail

import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel
import io.reactivex.Observable

interface ArtistDetailUseCase {
    fun artistModelById(id: String): Observable<ArtistModel>
}