package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.repository.AlbumRepository
import com.nimtego.plectrum.presentation.mvp.view_model.music.AlbumModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class AlbumInteractor @Inject constructor(
        disposable: CompositeDisposable,
        repository: AlbumRepository
) : BaseInteractor<List<AlbumModel>, AlbumInteractor.Params>(disposable, repository) {
    override fun buildUseCaseObservable(params: Params): Observable<List<AlbumModel>> {
//        Preconditions.checkNotNull(params)
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