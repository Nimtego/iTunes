package com.nimtego.plectrum.data.network.rss_itunes

import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface RssItunesApi {
    @GET
    fun getPopularContent(@Url url: String): Observable<PopularResponse>
}