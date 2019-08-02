package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.repository.PopularBookRepository
import com.nimtego.plectrum.data.repository.repository.PopularMovieRepository
import com.nimtego.plectrum.data.repository.repository.PopularMusicRepository
import com.nimtego.plectrum.presentation.interactor.LaunchUseCase
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
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
        private val schedulersProvider: SchedulersProvider,
        private val popularMusicRepository: PopularMusicRepository,
        private val popularMovieRepository: PopularMovieRepository,
        private val popularBookRepository: PopularBookRepository
) : LaunchUseCase  {

    override fun appLaunch(): Completable {
        return popularMusic().subscribeOn(schedulersProvider.io())
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