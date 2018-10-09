package com.nimtego.itunes;

import android.app.Application;

import com.nimtego.itunes.data.entity.AlbumsModel;
import com.nimtego.itunes.model.ModelManager;
import com.nimtego.itunes.data.rest.network.ITunesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static ITunesApi iTunes;
    private static ModelManager modelManager;

    public static ModelManager getModelManager() {
        return modelManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iTunes = retrofit.create(ITunesApi.class);
        modelManager = new AlbumsModel();
    }

    public static ITunesApi getApi() {
        return iTunes;
    }
}
