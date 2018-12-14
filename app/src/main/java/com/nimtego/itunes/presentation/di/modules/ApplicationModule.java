package com.nimtego.itunes.presentation.di.modules;

import android.content.Context;

import com.nimtego.itunes.App;
import com.nimtego.itunes.data.repository.AppRepository;
import com.nimtego.itunes.domain.Repository;

import javax.inject.Singleton;

import dagger.Provides;

public class ApplicationModule {
    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provaideApplivationContext() {
        return this.app;
    }

    @Provides
    @Singleton
    Repository provaideAppRepository(AppRepository appRepository) {
        return appRepository;
    }
}
