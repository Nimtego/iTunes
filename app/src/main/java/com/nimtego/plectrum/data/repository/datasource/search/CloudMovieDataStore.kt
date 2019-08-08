package com.nimtego.plectrum.data.repository.datasource.search

import com.nimtego.plectrum.data.model.itunes.MovieResult
import com.nimtego.plectrum.data.network.itunes.ItunesFabricParam
import com.nimtego.plectrum.data.network.itunes.ITunesApi
import com.nimtego.plectrum.domain.repository.MovieSource
import io.reactivex.Observable
import javax.inject.Inject

class CloudMovieDataStore @Inject constructor (
        private val api: ITunesApi
        //private val cache: PopularResponseCache
) : MovieSource<MovieResult> {

    override fun getMovieByRequest(request: String): Observable<List<MovieResult>> {
        return this.api.searchMovie(ItunesFabricParam.searchMovie(request)).map { it.results }
    }

    override fun getMovieById(id: Int): Observable<MovieResult> {
        return this.api.getMovie(ItunesFabricParam.lookupMovie(id)).map { it.results.first() }
    }

}