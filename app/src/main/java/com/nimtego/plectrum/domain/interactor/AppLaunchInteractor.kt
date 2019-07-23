package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.repository.PopularBookRepository
import com.nimtego.plectrum.data.repository.repository.PopularMovieRepository
import com.nimtego.plectrum.data.repository.repository.PopularMusicRepository
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AppLaunchInteractor @Inject constructor (
        disposable: CompositeDisposable,
        private val popularMusicRepository: PopularMusicRepository,
        private val popularMovieRepository: PopularMovieRepository,
        private val popularBookRepository: PopularBookRepository
) : BaseDisposableInteractor(disposable) {

    fun coldStart(completable: DisposableCompletableObserver, params: Params) {
        val observable = popularMusic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith(completable))
    }

    private fun popularMusic(): Completable {
        return popularMusicRepository.query("").ignoreElements()
    }

    private fun popularMovie(): Completable {
        return popularMovieRepository.query("").ignoreElements()
    }

    private fun popularBook(): Completable {
        return popularBookRepository.query("").ignoreElements()
    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }

        }
    }

}