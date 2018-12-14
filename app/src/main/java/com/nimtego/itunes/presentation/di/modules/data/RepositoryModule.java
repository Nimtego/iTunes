package com.nimtego.itunes.presentation.di.modules.data;

import android.content.Context;

import com.nimtego.itunes.data.cache.Cache;
import com.nimtego.itunes.data.entity.mapper.EntityDataMapper;
import com.nimtego.itunes.data.repository.AppRepository;
import com.nimtego.itunes.data.repository.datasource.DataStoreFactory;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.di.modules.ActivityModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ActivityModule.class)
public class RepositoryModule {

    @Provides
    @Singleton
    Repository provaideAppRepository(DataStoreFactory dataStoreFactory,
                                     EntityDataMapper mapper) {
        return new AppRepository(dataStoreFactory, mapper);
    }

    @Provides
    DataStoreFactory dataStoreFactory(Context context, Cache cache) {
        return new DataStoreFactory(context, cache);
    }
}
