package com.nimtego.plectrum.data.rest.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nimtego.plectrum.data.rest.network.itunes.ITunesApi;
import com.nimtego.plectrum.data.rest.network.wiki.RestCountries;
import com.nimtego.plectrum.data.rest.network.wiki.WikiApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConnection implements AppNetwork {
    private final static String BASE_URL_WIKI = "https://COUNTRY.wikipedia.org/w/";
    private final static String BASE_URL_ITUNES = "https://itunes.apple.com";

    private static AppNetwork appNetwork;
    private ITunesApi iTunesApi = null;
    private WikiApi wikiApi = null;
    private RestCountries restCountries = RestCountries.ENGLISH; //def

    public static AppNetwork getInstance() {
        if (appNetwork == null) {
            appNetwork = new NetworkConnection();
        }
        return appNetwork;
    }

    @Override
    public ITunesApi getITunesClient() {
        if (iTunesApi == null)
            iTunesApi = getITunes().create(ITunesApi.class);
        return iTunesApi;
    }

    @Override
    public WikiApi getWikiClient(RestCountries restCountries) {
        if (wikiApi == null || !this.restCountries.equals(restCountries)) {
            this.restCountries = restCountries;
            wikiApi = getWiki(this.restCountries).create(WikiApi.class);
        }
        return wikiApi;
    }

    @Override
    public WikiApi getWikiClient() {
        return getWikiClient(restCountries);
    }


    private Retrofit getITunes() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_ITUNES)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Retrofit getWiki(RestCountries restCountries) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_WIKI.replace("COUNTRY", restCountries.toString()))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Retrofit getWiki() {
        return getWiki(restCountries);
    }
}