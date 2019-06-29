package com.nimtego.plectrum.data.network.itunes

import com.nimtego.plectrum.data.model.itunes.AlbumsRepository
import com.nimtego.plectrum.data.model.itunes.ArtistsRepository
import com.nimtego.plectrum.data.model.itunes.SongsRepository

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ITunesApi {

    @GET("/search")
    fun searchArtist(@QueryMap param: Map<String, String>): Observable<ArtistsRepository>

    @GET("/search")
    fun searchAlbum(@QueryMap param: Map<String, String>): Observable<AlbumsRepository>

    @GET("/search")
    fun searchSongs(@QueryMap param: Map<String, String>): Observable<SongsRepository>

    @GET("/lookup")
    fun getSongs(@QueryMap param: Map<String, String>): Observable<SongsRepository>

    @GET("/lookup")
    fun getArtist(@QueryMap param: Map<String, String>): Observable<ArtistsRepository>

    @GET("/lookup")
    fun getAlbum(@QueryMap param: Map<String, String>): Observable<AlbumsRepository>
}
