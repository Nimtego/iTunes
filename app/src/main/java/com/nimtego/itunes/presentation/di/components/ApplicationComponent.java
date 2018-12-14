package com.nimtego.itunes.presentation.di.components;

import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Repository appRepository();
}
