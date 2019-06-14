package com.nimtego.plectrum.presentation.di.modules.data;

import android.content.Context;

import com.nimtego.plectrum.App;
import com.nimtego.plectrum.data.cache.Cache;
import com.nimtego.plectrum.data.cache.CacheK;
import com.nimtego.plectrum.data.cache.DashBoardEntityCache;
import com.nimtego.plectrum.data.cache.FileManager;
import com.nimtego.plectrum.data.cache.Serializer;
import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper;
import com.nimtego.plectrum.data.executor.BaseExecutor;
import com.nimtego.plectrum.data.model.rss_itunes.Feed;
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse;
import com.nimtego.plectrum.data.repository.repository.AppRepository;
import com.nimtego.plectrum.data.repository.datasource.DataStoreFactory;
import com.nimtego.plectrum.data.repository.repository.DashBoardRepository;
import com.nimtego.plectrum.domain.executor.ThreadExecutor;
import com.nimtego.plectrum.domain.repository.Repository;
import com.nimtego.plectrum.domain.repository.RepositoryK;
import com.nimtego.plectrum.presentation.di.modules.ActivityModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ActivityModule.class)
public class RepositoryModule {

    @Provides
    @Singleton
    RepositoryK provideDashBoardRepository(EntityDataMapper mapper,
                                                   DataStoreFactory<PopularResponse> dataStoreFactory) {
        return new DashBoardRepository(dataStoreFactory, mapper);
    }

    @Provides
    EntityDataMapper entityDataMapper() {
        return new EntityDataMapper();
    }

    @Provides
    Serializer serializer() {
        return new Serializer();
    }

    @Provides
    FileManager fileManager() {
        return new FileManager();
    }

    @Provides
    CacheK<PopularResponse> cache(Context appContext,
                                        Serializer serializer,
                                        FileManager fileManager,
                                        ThreadExecutor threadExecutor) {
        return new DashBoardEntityCache<>(appContext, serializer, fileManager, threadExecutor);
    }

    @Provides
    DataStoreFactory<PopularResponse> storeFactory(Context appContext, CacheK<PopularResponse> cache) {
        return new DataStoreFactory<>(appContext, cache);
    }

    @Provides
    ThreadExecutor executor () {
        return new BaseExecutor();
    }

}
