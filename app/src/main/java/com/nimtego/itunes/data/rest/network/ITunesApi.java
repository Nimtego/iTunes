package com.nimtego.itunes.data.rest.network;

import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ITunesApi {

    @GET("/search")
    Observable<ArtistsRepository> searchAutor(@QueryMap Map<String, String> param);

    @GET("/search")
    Observable<AlbumsRepository> searchAlbum(@QueryMap Map<String, String> param);

    @GET("/lookup")
    Observable<SongsRepository> getSongs(@QueryMap Map<String, String> param);
}
