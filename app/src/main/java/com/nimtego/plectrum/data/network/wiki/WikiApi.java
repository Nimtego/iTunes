package com.nimtego.plectrum.data.network.wiki;

import com.nimtego.plectrum.data.model.wiki.WikiSearchResult;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WikiApi {
    @GET("api.php")
    Observable<WikiSearchResult> searchArtist(@QueryMap Map<String, String> param);
}
