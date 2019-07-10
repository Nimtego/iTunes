package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.repository.datasource.DataStoreFactory
import com.nimtego.plectrum.domain.repository.RepositoryK
import io.reactivex.Observable

class MoreSectionRepository(
        private val dataStoreFactory: DataStoreFactory<PopularResponse>,
        private val mapper: EntityDataMapper
) : RepositoryK<List<Song>> {

    override fun query(request: String): Observable<List<Song>> {
        val dataStore = this.dataStoreFactory.createCloudDataStore()
//        var songType: Observable<PopularResponse>
//        when (request) {
//            "HOT" -> songType = dataStore.hot()
//        }
        return dataStore.topSong(2).map { mapper.topSong(it.feed) }
    }
}