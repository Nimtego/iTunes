package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.main.model.AlbumModelK
import com.nimtego.plectrum.presentation.main.model.ArtistModelK
import com.nimtego.plectrum.presentation.main.model.MainDataModelK
import com.nimtego.plectrum.presentation.main.model.SongModelK
import dagger.internal.Preconditions
import io.reactivex.Observable
import io.reactivex.functions.Function3
import java.util.function.BiFunction


class MainViewInteractorK : BaseInteractorK<MainDataModelK, MainViewInteractorK.Params>() {

    override fun buildUseCaseObservable(params: MainViewInteractorK.Params): Observable<MainDataModelK> {
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
                        MainDataModelK(artists, albums, songs)
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