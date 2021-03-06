package com.nimtego.plectrum.domain.interactor.detail

import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import com.nimtego.plectrum.domain.repository.detail.MusicalDetailRepository
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.detail.ArtistDetailUseCase
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistDetailModel
import io.reactivex.Observable
import javax.inject.Inject

class ArtistDetail @Inject constructor(
        private val schedulersProvider: SchedulersProvider,
        private val musicalDetailRepository: MusicalDetailRepository
) : ArtistDetailUseCase {

    override fun artistModelById(id: String): Observable<ArtistDetailModel> {
        return this.musicalDetailRepository.getArtistById(id)
                .subscribeOn(schedulersProvider.io())
    }

    class Params private constructor(
            val request: SectionsKey,
            val responseSize: Int) {

        companion object {

            fun forRequest(request: SectionsKey): Params {
                return forRequestWithSize(request, 0)
            }

            fun forRequestWithSize(request: SectionsKey, responseSize: Int): Params {
                return Params(request, responseSize)
            }

        }
    }
}