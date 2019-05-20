package com.nimtego.plectrum.presentation.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context() {
        return this.context.getApplicationContext();
    }
}
