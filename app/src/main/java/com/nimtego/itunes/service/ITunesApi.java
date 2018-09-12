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
    Call<EntityRepository> getData(@QueryMap Map<String, String> param);
}
