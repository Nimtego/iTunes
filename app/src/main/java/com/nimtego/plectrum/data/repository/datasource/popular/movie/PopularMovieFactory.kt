package com.nimtego.plectrum.data.repository.datasource.popular.movie

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.repository.datasource.popular.music.CloudPopularMovie
import com.nimtego.plectrum.data.repository.datasource.popular.music.DiskPopularMovie
import io.reactivex.Observable
import javax.inject.Inject

class PopularMovieFactory @Inject constructor(
        private val cache: Cache<String, PopularResponse>,
        private val cloudDataStore: CloudPopularMovie,
        private val diskPopularMusic: DiskPopularMovie
) : PopularMovieDataStore {



    override fun topMovie(): Observable<PopularResponse> {
        return if (cache.isCached(PopularMovieKey.TOP_MOVIE)) {
            diskPopularMusic.hotTrack()
        } else {
            cloudDataStore.hotTrack()
        }
    }
}