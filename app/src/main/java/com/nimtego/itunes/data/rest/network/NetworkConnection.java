package com.nimtego.itunes.data.rest.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nimtego.itunes.data.rest.network.itunes.ITunesApi;
import com.nimtego.itunes.data.rest.network.wiki.WikiApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConnection {
    private final static String BASE_URL_WIKI = "https://en.wikipedia.org/w/";
    private final static String BASE_URL_ITUNES = "https://itunes.apple.com";

    private static NetworkConnection apiClient;
    private ITunesApi iTunesApi = null;
    private WikiApi wikiApi = null;

    public static NetworkConnection getInstance() {
        if (apiClient == null) {
            apiClient = new NetworkConnection();
        }
        return apiClient;
    }


    public ITunesApi getITunesClient() {
        if (iTunesApi == null)
            iTunesApi = getITunes().create(ITunesApi.class);
        return iTunesApi;
    }

    public WikiApi getWikiClient() {
        if (wikiApi == null)
            wikiApi = getWiki().create(WikiApi.class);
        return wikiApi;
    }


    private Retrofit getITunes() {
        return new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Retrofit getWiki() {
        return new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}