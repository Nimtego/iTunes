package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicKey
import com.nimtego.plectrum.data.repository.repository.PopularBookRepository
import com.nimtego.plectrum.data.repository.repository.PopularMovieRepository
import com.nimtego.plectrum.data.repository.repository.PopularMusicRepository
import com.nimtego.plectrum.presentation.interactor.LaunchUseCase
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import io.reactivex.Completable
import javax.inject.Inject

class AppLaunchInteractor @Inject constructor (
        private val schedulersProvider: SchedulersProvider,
        private val musicRepository: PopularMusicRepository,
        private val movieRepository: PopularMovieRepository,
        private val bookRepository: PopularBookRepository
) : LaunchUseCase  {

    override fun appLaunch(): Completable {
        return popularMusic().subscribeOn(schedulersProvider.io())
    }

    private fun popularMusic(): Completable {
        //todo change late
        return musicRepository.query(PopularMusicKey.TOP_ALBUM, 5).ignoreElements()
    }

//    private fun popularMovie(): Completable {
//        return movieRepository.query("").ignoreElements()
//    }
//
//    private fun popularBook(): Completable {
//        return bookRepository.query("").ignoreElements()
//    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }

        }
    }

}