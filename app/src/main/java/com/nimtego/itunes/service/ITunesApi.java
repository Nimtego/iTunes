package com.nimtego.itunes.service;

import com.nimtego.itunes.service.pojo.AlbumsRepository;
import com.nimtego.itunes.service.pojo.ArtistsRepository;
import com.nimtego.itunes.service.pojo.SongsRepository;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ITunesApi {

    @GET("/search")
    Call<ArtistsRepository> getAutorSongs(@QueryMap Map<String, String> param);

    @GET("/search")
    Call<AlbumsRepository> searchAlbum(@QueryMap Map<String, String> param);

    @GET("/lookup")
    Call<SongsRepository> getSongs(@QueryMap Map<String, String> param);
}
