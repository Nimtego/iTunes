package com.nimtego.itunes.presentation.di.modules.data;

import com.nimtego.itunes.data.entity.mapper.EntityDataMapper;
import com.nimtego.itunes.data.repository.AppRepository;
import com.nimtego.itunes.data.repository.datasource.DataStoreFactory;
import com.nimtego.itunes.domain.Repository;

import javax.inject.Singleton;

import dagger.Provides;

public class RepositoryModule {

    @Provides
    @Singleton
    Repository provaideAppRepository(DataStoreFactory dataStoreFactory,
                                     EntityDataMapper mapper) {
        return new AppRepository(dataStoreFactory, mapper);
    }
}
