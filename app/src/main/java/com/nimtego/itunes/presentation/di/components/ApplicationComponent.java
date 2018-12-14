package com.nimtego.itunes.presentation.di.components;

import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.di.modules.data.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RepositoryModule.class)
public interface ApplicationComponent {
    Repository appRepository();
}
