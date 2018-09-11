package com.nimtego.itunes.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ITunesApi {

    /*
    *
    *
    https://itunes.apple.com/search?term=metallica&entity=album&limit=5&attribute=albumTerm
    */
    @GET("search&entity=album&attribute=albumTerm")
    Call<EntityRepository> getData(@Query("term") String resourceName, @Query("limit") int count);
}
