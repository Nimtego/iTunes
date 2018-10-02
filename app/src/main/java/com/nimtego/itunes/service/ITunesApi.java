package com.nimtego.itunes.service;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ITunesApi {

    @GET("/search")
    Call<EntityRepository> getAutorSongs(@QueryMap Map<String, String> param);

    @GET("/search")
    Call<EntityRepository> getAlbum(@QueryMap Map<String, String> param);

    @GET("/lookup")
    Call<SongsRepository> getSongs(@QueryMap Map<String, String> param);
}
