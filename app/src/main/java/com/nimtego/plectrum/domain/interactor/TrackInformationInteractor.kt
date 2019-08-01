package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.data.repository.repository.PopularBookRepository
import com.nimtego.plectrum.data.repository.repository.PopularMovieRepository
import com.nimtego.plectrum.data.repository.repository.PopularMusicRepository
import com.nimtego.plectrum.data.repository.repository.SongRepository
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrackInformationInteractor @Inject constructor (
        disposable: CompositeDisposable,
        private val songRepository: SongRepository
) : BaseDisposableInteractor(disposable) {

//    fun getTrackById(observer: DisposableObserver<Song>, param: Params) {
//        val observable = songRepository.getSongById(param.id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//        addDisposable(observable.subscribeWith(observer))
//    }
    fun getTrackById(param: Params) = songRepository.getSongById(param.id)


    class Params private constructor(val request: String, val id: Int) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request, 0)
            }

            fun forId(id: Int): Params {
                return Params("", id)
            }

        }
    }

}