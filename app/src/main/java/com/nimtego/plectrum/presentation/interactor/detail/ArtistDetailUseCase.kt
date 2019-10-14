package com.nimtego.plectrum.presentation.interactor.detail

import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel
import io.reactivex.Single

interface ArtistDetailUseCase {
    fun artistModelById(id: String): Single<ArtistModel>
}