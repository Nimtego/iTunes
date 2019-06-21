package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.entity.DashBoardSongsModel
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.data.repository.repository.DashBoardRepository
import com.nimtego.plectrum.data.repository.repository.MoreSectionRepository
import com.nimtego.plectrum.data.repository.repository.TabContentRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoreSectionInteractor @Inject constructor (
        disposable: CompositeDisposable,
        repository: MoreSectionRepository
) : BaseInteractorK<List<Song>, MoreSectionInteractor.Params>(disposable, repository) {

    override fun buildUseCaseObservable(params: Params): Observable<List<Song>> {
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