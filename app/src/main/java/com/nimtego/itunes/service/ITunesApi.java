package com.nimtego.itunes.service;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ITunesApi {

    /*
    *
    *
    https://itunes.apple.com/search?term=metallica&entity=album&limit=5&attribute=albumTerm
    */
    @GET("/search")
    Call<EntityRepository> getAlbum(@QueryMap Map<String, String> param);

    //https://itunes.apple.com/search?id=211192863&entity=song&media=music
    @GET("/search")
    Call<SongResult> getSongs(@QueryMap Map<String, String> param);
}
