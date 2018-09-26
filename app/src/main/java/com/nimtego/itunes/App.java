package com.nimtego.itunes;

import android.app.Application;

import com.nimtego.itunes.model.AlbumsModel;
import com.nimtego.itunes.model.ModelManager;
import com.nimtego.itunes.service.ITunesApi;
import com.nimtego.itunes.service.ResultEntity;

import java.lang.reflect.Modifier;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static ITunesApi iTunes;
    private static ModelManager<ResultEntity> modelManager;

    public static ModelManager<ResultEntity> getModelManager() {
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
