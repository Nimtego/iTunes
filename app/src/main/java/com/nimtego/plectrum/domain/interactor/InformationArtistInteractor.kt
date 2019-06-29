package com.nimtego.plectrum.domain.interactor

import com.nimtego.plectrum.presentation.old.information_view.artist.model.ArtistDetailsModelK
import com.nimtego.plectrum.presentation.old.main.model.AlbumModel
import dagger.internal.Preconditions
import io.reactivex.Observable
import java.util.*

class InformationArtistInteractor : BaseInteractor<ArtistDetailsModelK, InformationArtistInteractor.Params>() {
    override fun buildUseCaseObservable(params: Params): Observable<ArtistDetailsModelK> {
        Preconditions.checkNotNull(params)
        return repository.artistDetail(params.request).map { result ->
            val albumModels = duplicateRemove(Objects.requireNonNull<List<AlbumModel>>(result.albums))
            //todo
            //result.setAlbums(albumModels);
            result
        }
    }

    private fun duplicateRemove(albumModels: List<AlbumModel>): List<AlbumModel> {
        val duplicateCheck = HashMap<String, AlbumModel>()
        albumModels.forEach {album ->
            album.albumName.let { duplicateCheck[it] = album }
        }
        return ArrayList(duplicateCheck.values)
    }

    class Params private constructor(val request: String) {
        companion object {
            fun forRequest(request: String): Params {
                return Params(request)
            }
        }
    }
}
