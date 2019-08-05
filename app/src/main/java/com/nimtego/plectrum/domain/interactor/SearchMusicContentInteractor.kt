package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.repository.PopularBookRepository
import com.nimtego.plectrum.data.repository.repository.PopularMovieRepository
import com.nimtego.plectrum.data.repository.repository.PopularMusicRepository
import com.nimtego.plectrum.data.repository.repository.SongRepository
import com.nimtego.plectrum.domain.repository.SongSource
import com.nimtego.plectrum.presentation.interactor.LaunchUseCase
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.SongSearchUseCase
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SearchMusicContentInteractor @Inject constructor (
        private val schedulersProvider: SchedulersProvider,
        private val songRepository: SongSource<Song>
) : SongSearchUseCase {

    override fun searchSong(request: String): Observable<List<Song>> {
        return this.songRepository.getSongsByRequest(request)
                .subscribeOn(schedulersProvider.io())

    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }

        }
    }

}