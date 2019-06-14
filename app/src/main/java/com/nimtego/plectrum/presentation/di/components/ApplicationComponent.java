package com.nimtego.plectrum.presentation.di.components;

import com.nimtego.plectrum.domain.repository.Repository;
import com.nimtego.plectrum.domain.repository.RepositoryK;
import com.nimtego.plectrum.presentation.di.modules.data.RepositoryModule;
import com.nimtego.plectrum.presentation.di.modules.presentation.PresenterModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class, PresenterModule.class})
public interface ApplicationComponent {
    RepositoryK appRepository();
}
