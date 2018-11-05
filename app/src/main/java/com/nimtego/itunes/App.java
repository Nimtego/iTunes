package com.nimtego.itunes;

import android.app.Application;
import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nimtego.itunes.data.rest.network.ITunesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static ITunesApi iTunes;
    public static Context context; // TODO: 05.11.2018 tmp 


    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        iTunes = retrofit.create(ITunesApi.class);
        context = getApplicationContext();

    }

    public static Context getAppContext() {
        return context;
    }


    public static ITunesApi getApi() {
        return iTunes;
    }
}
