package com.nimtego.plectrum.presentation.di.modules.data

import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper
import com.nimtego.plectrum.data.entity.mapper.PopularMusicMapper
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicFactory
import com.nimtego.plectrum.data.repository.repository.*
import com.nimtego.plectrum.presentation.di.modules.ContextModule
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class, DataStoreModule::class])
class RepositoryModule {


    @Provides
    @Singleton
    internal fun providePopularMusicRepository(mapper: PopularMusicMapper,
                                               dataStoreFactory: PopularMusicFactory) =
            PopularMusicRepository(dataStoreFactory, mapper)

    @Provides
    @Singleton
    internal fun provideMoreSectionRepository(mapper: PopularMusicMapper,
                                              dataStoreFactory: PopularMusicFactory) =
            MoreSectionRepository(dataStoreFactory, mapper)


    @Provides
    internal fun entityDataMapper(): EntityDataMapper {
        return EntityDataMapper()
    }

    @Provides
    internal fun providePopularMusicMapper(): PopularMusicMapper {
        return PopularMusicMapper()
    }

}