package com.nimtego.itunes;

import android.app.Application;

import com.nimtego.itunes.service.ITunesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static ITunesApi iTunes;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iTunes = retrofit.create(ITunesApi.class);
    }

    public static ITunesApi getApi() {
        return iTunes;
    }
}
