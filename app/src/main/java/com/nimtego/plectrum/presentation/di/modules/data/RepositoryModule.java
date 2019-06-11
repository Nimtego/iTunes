package com.nimtego.plectrum.presentation.di.modules.data;

import com.nimtego.plectrum.App;
import com.nimtego.plectrum.data.cache.AlbumCache;
import com.nimtego.plectrum.data.cache.CacheK;
import com.nimtego.plectrum.data.cache.DashBoardEntityCache;
import com.nimtego.plectrum.data.cache.FileManager;
import com.nimtego.plectrum.data.cache.Serializer;
import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper;
import com.nimtego.plectrum.data.executor.BaseExecutor;
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse;
import com.nimtego.plectrum.data.repository.AppRepository;
import com.nimtego.plectrum.data.repository.datasource.DataStoreFactory;
import com.nimtego.plectrum.domain.Repository;
import com.nimtego.plectrum.domain.executor.ThreadExecutor;
import com.nimtego.plectrum.presentation.di.modules.ActivityModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ActivityModule.class)
public class RepositoryModule {

    @Provides
    @Singleton
    Repository provaideAppRepository(EntityDataMapper mapper) {
        DataStoreFactory<PopularResponse> dataStoreFactory = new DataStoreFactory<>(App.Companion.getINSTANCE().getApplicationContext(),
                new DashBoardEntityCache<>(App.Companion.getINSTANCE().getApplicationContext(),
                        new Serializer(),
                        new FileManager(),
                new BaseExecutor()));
        return new AppRepository(dataStoreFactory, mapper);
    }

//    @Provides
//    DataStoreFactory<PopularResponse> dataStoreFactory() {
//        return new DataStoreFactory<>(App.Companion.getINSTANCE().getApplicationContext(),
//                new DashBoardEntityCache<>(App.Companion.getINSTANCE().getApplicationContext(),
//                        new Serializer(),
//                        new FileManager(),
//                new BaseExecutor()));
//    }

    @Provides
    EntityDataMapper entityDataMapper() {
        return new EntityDataMapper();
    }

}
