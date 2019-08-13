package com.nimtego.plectrum.data.repository.datasource.search

import com.nimtego.plectrum.data.model.itunes.MovieResult
import com.nimtego.plectrum.domain.repository.MovieSource
import io.reactivex.Observable
import javax.inject.Inject

class MovieDataStoreFactory @Inject constructor(
        //private val cache: PopularResponseCache,
        private val cloudDataStore: MovieSource<MovieResult>
        //private val diskDataStore: DiskMusicDataStore
) : MovieSource<MovieResult> by cloudDataStore