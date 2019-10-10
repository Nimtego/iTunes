package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.datasource.popular.book.PopularBookKey
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieKey
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicKey
import com.nimtego.plectrum.data.repository.repository.PopularBookRepository
import com.nimtego.plectrum.data.repository.repository.PopularMovieRepository
import com.nimtego.plectrum.data.repository.repository.PopularMusicRepository
import com.nimtego.plectrum.presentation.interactor.LaunchUseCase
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AppLaunchInteractor @Inject constructor (
        private val schedulersProvider: SchedulersProvider,
        private val musicRepository: PopularMusicRepository,
        private val movieRepository: PopularMovieRepository,
        private val bookRepository: PopularBookRepository
) : LaunchUseCase  {

    override fun appLaunch(): Completable {
        return Completable.mergeArrayDelayError(
                popularMusic().subscribeOn(schedulersProvider.io()),
                popularMovie().subscribeOn(schedulersProvider.io()),
                popularBook().subscribeOn(schedulersProvider.io())
        )
    }

    private fun popularMusic(): Completable {
        return musicRepository.query(PopularMusicKey.TOP_ALBUM, 5).ignoreElements()
    }

    private fun popularMovie(): Completable {
        return movieRepository.query(PopularMovieKey.TOP_MOVIE, 5).ignoreElements()
    }

    private fun popularBook(): Completable {
        return bookRepository.query(PopularBookKey.TOP_FREE_BOOK, 5).ignoreElements()
    }

}