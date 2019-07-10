package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper
import com.nimtego.plectrum.data.repository.datasource.DataStoreFactory
import com.nimtego.plectrum.domain.repository.RepositoryK
import com.nimtego.plectrum.presentation.mvp.view_model.information_view.SongDetailsModel
import io.reactivex.Observable

class InformationRepository(
        private val dataStoreFactory: DataStoreFactory<Song>,
        private val mapper: EntityDataMapper
) : RepositoryK<SongDetailsModel> {

    override fun query(request: String): Observable<SongDetailsModel> {
        val dataStore = this.dataStoreFactory.createCloudDataStore()
        return dataStore.songById(request).map { mapper.transformSongDetail(it.results[0]) }
    }
}