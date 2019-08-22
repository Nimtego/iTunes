package com.nimtego.plectrum.domain.interactor.general

import com.nimtego.plectrum.data.repository.repository.SongRepository
import com.nimtego.plectrum.domain.interactor.base.BaseDisposableInteractor
import io.reactivex.disposables.CompositeDisposable
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