package com.nimtego.plectrum.data.network.itunes

import com.nimtego.plectrum.data.model.itunes.AlbumsResultRepository
import com.nimtego.plectrum.data.model.itunes.ArtistsResultRepository
import com.nimtego.plectrum.data.model.itunes.MovieResultRepository
import com.nimtego.plectrum.data.model.itunes.SongsResultRepository

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ITunesApi {

    @GET("/search")
    fun searchArtist(@QueryMap param: Map<String, String>): Observable<ArtistsResultRepository>

    @GET("/search")
    fun searchAlbum(@QueryMap param: Map<String, String>): Observable<AlbumsResultRepository>

    @GET("/search")
    fun searchSongs(@QueryMap param: Map<String, String>): Observable<SongsResultRepository>

    @GET("/search")
    fun searchMovie(@QueryMap param: Map<String, String>): Observable<MovieResultRepository>

    @GET("/lookup")
    fun getMovie(@QueryMap param: Map<String, String>): Observable<MovieResultRepository>

    @GET("/lookup")
    fun getSongs(@QueryMap param: Map<String, String>): Observable<SongsResultRepository>

    @GET("/lookup")
    fun getArtist(@QueryMap param: Map<String, String>): Observable<ArtistsResultRepository>

    @GET("/lookup")
    fun getAlbum(@QueryMap param: Map<String, String>): Observable<AlbumsResultRepository>


}
