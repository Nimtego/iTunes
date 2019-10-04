package com.nimtego.plectrum.domain.interactor.popular

import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicKey
import com.nimtego.plectrum.data.repository.repository.PopularMusicRepository
import com.nimtego.plectrum.domain.interactor.base.BaseInteractor
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularMusicInteractor @Inject constructor(
        disposable: CompositeDisposable,
        private val repositoryMusicRepository: PopularMusicRepository
) : BaseInteractor<BaseParentViewModel<ChildViewModel>, PopularMusicInteractor.Params>(disposable) {

    override fun buildUseCaseObservable(params: Params): Observable<BaseParentViewModel<ChildViewModel>> {
        return repositoryMusicRepository.query(
                params.request,
                params.responseSize
        )
    }

    class Params private constructor(
            val request: PopularMusicKey,
            val responseSize: Int) {

        companion object {

            fun forRequest(request: PopularMusicKey): Params {
                return forRequestWithSize(request, 100)
            }

            fun forRequestWithSize(request: PopularMusicKey, responseSize: Int): Params {
                return Params(request, responseSize)
            }

        }
    }

}