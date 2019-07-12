package com.nimtego.plectrum.presentation.di.modules.data

import android.content.Context
import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.cache.FileManager
import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.cache.Serializer
import com.nimtego.plectrum.data.executor.BaseExecutor
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import com.nimtego.plectrum.data.repository.datasource.popular.music.CloudPopularMusic
import com.nimtego.plectrum.data.repository.datasource.popular.music.DiskPopularMusic
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicFactory
import com.nimtego.plectrum.domain.executor.ThreadExecutor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataStoreModule {


    @Provides
    internal fun serializer(): Serializer {
        return Serializer()
    }

    @Provides
    @Singleton
    internal fun fileManager(): FileManager {
        return FileManager()
    }

    @Provides
    internal fun executor(): ThreadExecutor {
        return BaseExecutor()
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
    internal fun provideMusicDataStoreCache(
            appContext: Context,
            serializer: Serializer,
            fileManager: FileManager,
            threadExecutor: ThreadExecutor
    ) : Cache<String, PopularResponse> {
        return PopularResponseCache(appContext, serializer, fileManager, threadExecutor)
    }

    @Provides
    @Singleton
    internal fun provideMusicDataStoreFactory(
            @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
            cache: Cache<String, PopularResponse>,
            cloudPopularMusic: CloudPopularMusic,
            discPopularMusic: DiskPopularMusic
    ) = PopularMusicFactory(cache, cloudPopularMusic, discPopularMusic)

    @Provides
    @Singleton
    internal fun provideCloudPopularMusicDataStore(
            @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
            cache: Cache<String, PopularResponse>,
            @Named(NetworkQualifiers.RSS_ITUNES_API)
            api: RssItunesApi
    ) =  CloudPopularMusic(api, cache)

    @Provides
    @Singleton
    internal fun provideDiscPopularMusicDataStore(
            @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
            cache: Cache<String, PopularResponse>
    ) = DiskPopularMusic(cache)

}