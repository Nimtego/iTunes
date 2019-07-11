package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper
import com.nimtego.plectrum.data.repository.datasource.DataStoreFactory
import com.nimtego.plectrum.domain.repository.Repository
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumModel
import io.reactivex.Observable

class AlbumRepository(
        private val dataStoreFactory: DataStoreFactory<List<AlbumModel>>,
        private val mapper: EntityDataMapper
) : Repository<List<AlbumModel>> {

    override fun query(request: String): Observable<List<AlbumModel>> {
        val dataStore = this.dataStoreFactory.createCloudDataStore()
        return dataStore.albums(request).map { this.mapper.transformAlbums(it) }
    }
}