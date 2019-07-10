package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.data.repository.repository.InformationRepository
import com.nimtego.plectrum.data.repository.repository.MoreSectionRepository
import com.nimtego.plectrum.presentation.mvp.view_model.information_view.SongDetailsModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class InformationInteractor @Inject constructor (
        disposable: CompositeDisposable,
        repository: InformationRepository
) : BaseInteractor<SongDetailsModel, InformationInteractor.Params>(disposable, repository) {

    override fun buildUseCaseObservable(params: Params): Observable<SongDetailsModel> {
        return repository.query(params.request)
    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }

        }
    }

}