package com.nimtego.itunes.data.rest.network;

import com.nimtego.itunes.data.rest.pojo.wiki.WikiSearchResult;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WikiApi {
    @GET("api.php")
    Observable<WikiSearchResult> searchArtist(@QueryMap Map<String, String> param);
}
