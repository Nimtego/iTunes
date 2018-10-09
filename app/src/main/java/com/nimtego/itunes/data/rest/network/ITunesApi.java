package com.nimtego.itunes.data.rest.network;

import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;

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
