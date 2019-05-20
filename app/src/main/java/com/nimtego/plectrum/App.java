package com.nimtego.plectrum;

import android.app.Application;
import android.content.Context;

import com.nimtego.plectrum.data.repository.AppRepository;
import com.nimtego.plectrum.domain.Repository;
import com.nimtego.plectrum.presentation.di.components.DaggerPresenterComponent;
import com.nimtego.plectrum.presentation.di.components.PresenterComponent;


public class App extends Application {

    public static Context context; // TODO: 05.11.2018 tmp
    private static Repository repository;
    private static PresenterComponent presenterComponent;

    public static Repository getRepository() {

        if (repository != null)
            return repository;
        return new AppRepository();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        presenterComponent = DaggerPresenterComponent.create();

    }


    public static PresenterComponent getComponent() {
        return presenterComponent;
    }

    public static Context getAppContext() {
        return context;
    }

}
