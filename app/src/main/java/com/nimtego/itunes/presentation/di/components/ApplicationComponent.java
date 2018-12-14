package com.nimtego.itunes.presentation.di.components;

import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.di.modules.data.RepositoryModule;
import com.nimtego.itunes.presentation.di.modules.presentation.PresenterModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class, PresenterModule.class})
public interface ApplicationComponent {
    Repository appRepository();

}
