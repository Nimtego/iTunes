package com.nimtego.plectrum.presentation.di.modules.data;

import com.nimtego.plectrum.App;
import com.nimtego.plectrum.data.cache.AlbumCache;
import com.nimtego.plectrum.data.cache.FileManager;
import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper;
import com.nimtego.plectrum.data.repository.AppRepository;
import com.nimtego.plectrum.data.repository.datasource.DataStoreFactory;
import com.nimtego.plectrum.domain.Repository;
import com.nimtego.plectrum.presentation.di.modules.ActivityModule;

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
    DataStoreFactory dataStoreFactory() {
        return new DataStoreFactory(App.Companion.getINSTANCE(),
                new AlbumCache(App.Companion.getINSTANCE(),
                        new FileManager()));
    }

    @Provides
    EntityDataMapper entityDataMapper() {
        return new EntityDataMapper();
    }

}
