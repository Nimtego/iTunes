package com.nimtego.itunes;

import android.app.Application;
import android.content.Context;

import com.nimtego.itunes.data.repository.AppRepository;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.di.components.ApplicationComponent;

public class App extends Application {

    public static Context context; // TODO: 05.11.2018 tmp
    private static Repository repository;
    private ApplicationComponent applicationComponent;

    public static Repository getRepository() {

        if(repository != null)
            return repository;
        return new AppRepository();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    private void initDiInjector() {
        // TODO: 14.12.2018  
    }

    public static Context getAppContext() {
        return context;
    }

}
