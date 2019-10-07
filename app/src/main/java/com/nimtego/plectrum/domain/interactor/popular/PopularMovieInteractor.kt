package com.nimtego.plectrum.domain.interactor.popular

import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieKey
import com.nimtego.plectrum.data.repository.repository.PopularMovieRepository
import com.nimtego.plectrum.domain.interactor.base.BaseInteractor
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularMovieInteractor @Inject constructor(
        disposable: CompositeDisposable,
        private val repositoryMovieRepository: PopularMovieRepository
) : BaseInteractor<BaseParentViewModel<ChildViewModel>, PopularMovieInteractor.Params>(disposable) {

    override fun buildUseCaseObservable(params: Params): Observable<BaseParentViewModel<ChildViewModel>> {
        return repositoryMovieRepository.query(params.request, params.responseSize)
    }

    class Params private constructor(
            val request: PopularMovieKey,
            val responseSize: Int) {

        companion object {

            fun forRequest(request: PopularMovieKey): Params {
                return forRequestWithSize(request, 100)
            }

            fun forRequestWithSize(request: PopularMovieKey, responseSize: Int): Params {
                return Params(request, responseSize)
            }

        }
    }

}