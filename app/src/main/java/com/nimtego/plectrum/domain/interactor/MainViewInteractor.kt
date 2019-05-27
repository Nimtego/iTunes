package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.main.model.MainDataModel
import dagger.internal.Preconditions
import io.reactivex.Observable
import io.reactivex.functions.Function3


class MainViewInteractor : BaseInteractor<MainDataModel, MainViewInteractor.Params>() {

    override fun buildUseCaseObservable(params: MainViewInteractor.Params): Observable<MainDataModel> {
        Preconditions.checkNotNull(params)
        val albumsObs = repository.albums(params.request)
        val songsObs = repository.songs(params.request)
        val artistsObs = repository.artists(params.request)
        //todo to ugly
        return Observable.combineLatest(
                    albumsObs,
                    songsObs,
                    artistsObs,
                    Function3 { albums,
                                songs,
                                artists ->
                        MainDataModel(artists, albums, songs)
                })
    }

    class Params private constructor(val request: String) {
        companion object {

            fun forRequest(request: String): Params {
                return Params(request)
            }
        }
    }

}