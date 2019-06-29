package com.nimtego.plectrum.data.network.wiki

import com.nimtego.plectrum.data.model.wiki.WikiSearchResult

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WikiApi {
    @GET("api.php")
    fun searchArtist(@QueryMap param: Map<String, String>): Observable<WikiSearchResult>
}
