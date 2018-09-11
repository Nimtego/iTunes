package com.nimtego.itunes;

import android.app.Application;

import com.nimtego.itunes.service.ITunesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static ITunesApi iTunes;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iTunes = retrofit.create(ITunesApi.class);
    }

    public static ITunesApi getApi() {
        return iTunes;
    }
}
