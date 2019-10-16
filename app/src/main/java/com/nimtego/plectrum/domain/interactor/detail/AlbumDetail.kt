package com.nimtego.plectrum.domain.interactor.detail

import com.nimtego.plectrum.data.repository.datasource.popular.SectionsKey
import com.nimtego.plectrum.domain.repository.detail.MusicalDetailRepository
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.detail.AlbumDetailUseCase
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumDetailModel
import io.reactivex.Observable
import javax.inject.Inject

class AlbumDetail @Inject constructor(
        private val schedulersProvider: SchedulersProvider,
        private val musicalDetailRepository: MusicalDetailRepository
) : AlbumDetailUseCase {

    override fun albumModelById(id: String): Observable<AlbumDetailModel> {
        return this.musicalDetailRepository.getAlbumById(id)
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