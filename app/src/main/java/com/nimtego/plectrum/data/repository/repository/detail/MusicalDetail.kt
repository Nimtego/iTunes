package com.nimtego.plectrum.data.repository.repository.detail

import com.nimtego.plectrum.data.model.mappers.MusicalDetailMapper
import com.nimtego.plectrum.data.repository.datasource.detail.DetailMusicalDataStore
import com.nimtego.plectrum.domain.repository.detail.MusicalDetailRepository
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import com.nimtego.plectrum.presentation.mvp.model.music.ArtistModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongModel
import io.reactivex.Single
import javax.inject.Inject

class MusicalDetail @Inject constructor(
        private val dataStoreFactory: DetailMusicalDataStore,
        private val mapper: MusicalDetailMapper
) : MusicalDetailRepository {

    override fun getSongById(id: String): Single<SongModel> {
        return this.dataStoreFactory.songById(id).map {
            this.mapper.songResultToModel(it)
        }
    }

    override fun getAlbumById(id: String): Single<AlbumModel> {
        return this.dataStoreFactory.albumById(id).flatMap { albumResult ->
            this.dataStoreFactory.songsByAlbumId(albumResult.collectionId.toString())
                    .map { songResult ->
                        this.mapper.albumResultToModel(albumResult, songResult)
                    }
        }
    }

    override fun getArtistById(id: String): Single<ArtistModel> {
        return this.dataStoreFactory.artistById(id).flatMap { artistResult ->
            this.dataStoreFactory.albumsByArtistId(artistResult.artistId.toString())
                    .map { albumResult ->
                        this.mapper.artistResultToModel(artistResult, albumResult)
                    }
        }
    }
}