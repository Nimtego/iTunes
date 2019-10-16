package com.nimtego.plectrum.domain.interactor.search

import com.nimtego.plectrum.domain.repository.SongSource
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.MusicalSearchUseCase
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import io.reactivex.Observable
import javax.inject.Inject

class SearchMusicContentInteractor @Inject constructor (
        private val schedulersProvider: SchedulersProvider,
        private val songRepository: SongSource<Song>
) : MusicalSearchUseCase {

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