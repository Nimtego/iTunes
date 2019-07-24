package com.nimtego.plectrum.presentation.di.modules.data

import android.content.Context
import com.nimtego.plectrum.data.cache.FileManager
import com.nimtego.plectrum.data.cache.PopularResponseCache
import com.nimtego.plectrum.data.cache.Serializer
import com.nimtego.plectrum.data.executor.BaseExecutor
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import com.nimtego.plectrum.data.repository.datasource.popular.book.CloudPopularBook
import com.nimtego.plectrum.data.repository.datasource.popular.book.DiskPopularBook
import com.nimtego.plectrum.data.repository.datasource.popular.book.PopularBookDataStore
import com.nimtego.plectrum.data.repository.datasource.popular.book.PopularBookFactory
import com.nimtego.plectrum.data.repository.datasource.popular.movie.CloudPopularMovie
import com.nimtego.plectrum.data.repository.datasource.popular.movie.DiskPopularMovie
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieDataStore
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieFactory
import com.nimtego.plectrum.data.repository.datasource.popular.music.CloudPopularMusic
import com.nimtego.plectrum.data.repository.datasource.popular.music.DiskPopularMusic
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicDataStore
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicFactory
import com.nimtego.plectrum.domain.executor.ThreadExecutor
import com.nimtego.plectrum.presentation.di.modules.domain.RepositoryQualifiers
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
    @Named(RepositoryQualifiers.MUSIC_REPOSITORY)
    internal fun provideMusicDataStoreCache(
            appContext: Context,
            serializer: Serializer,
            fileManager: FileManager,
            threadExecutor: ThreadExecutor
    ) : PopularResponseCache {
        return PopularResponseCache(appContext, serializer, fileManager, threadExecutor)
    }

    @Provides
    @Singleton
    internal fun provideMusicDataStoreFactory(
            @Named(RepositoryQualifiers.MUSIC_REPOSITORY)
            cache: PopularResponseCache,
            cloudPopularMusic: CloudPopularMusic,
            discPopularMusic: DiskPopularMusic
    ) : PopularMusicDataStore {
        return PopularMusicFactory(cache, cloudPopularMusic, discPopularMusic)
    }

    @Provides
    @Singleton
    @Named(RepositoryQualifiers.MUSIC_REPOSITORY)
    internal fun provideCloudPopularMusicDataStore(
            @Named(RepositoryQualifiers.MUSIC_REPOSITORY)
            cache: PopularResponseCache,
            @Named(NetworkQualifiers.RSS_ITUNES_API)
            api: RssItunesApi
    ) =  CloudPopularMusic(api, cache)

    @Provides
    @Singleton
    internal fun provideDiscPopularMusicDataStore(
            @Named(RepositoryQualifiers.MUSIC_REPOSITORY)
            cache: PopularResponseCache
    ) = DiskPopularMusic(cache)

    @Provides
    @Singleton
    @Named(RepositoryQualifiers.MOVIE_REPOSITORY)
    internal fun provideMovieDataStoreCache(
            appContext: Context,
            serializer: Serializer,
            fileManager: FileManager,
            threadExecutor: ThreadExecutor
    ) : PopularResponseCache {
        return PopularResponseCache(appContext, serializer, fileManager, threadExecutor)
    }

    @Provides
    @Singleton
    internal fun provideMovieDataStoreFactory(
            @Named(RepositoryQualifiers.MOVIE_REPOSITORY)
            cache: PopularResponseCache,
            cloudPopularMovie: CloudPopularMovie,
            discPopularMovie: DiskPopularMovie
    ) : PopularMovieDataStore {
        return PopularMovieFactory(cache, cloudPopularMovie, discPopularMovie)
    }

    @Provides
    @Singleton
    @Named(RepositoryQualifiers.MOVIE_REPOSITORY)
    internal fun provideCloudPopularMovieDataStore(
            @Named(RepositoryQualifiers.MOVIE_REPOSITORY)
            cache: PopularResponseCache,
            @Named(NetworkQualifiers.RSS_ITUNES_API)
            api: RssItunesApi
    ) =  CloudPopularMovie(api, cache)

    @Provides
    @Singleton
    internal fun provideDiscPopularMovieDataStore(
            @Named(RepositoryQualifiers.MOVIE_REPOSITORY)
            cache: PopularResponseCache
    ) = DiskPopularMovie(cache)

    @Provides
    @Singleton
    @Named(RepositoryQualifiers.BOOK_REPOSITORY)
    internal fun provideBookDataStoreCache(
            appContext: Context,
            serializer: Serializer,
            fileManager: FileManager,
            threadExecutor: ThreadExecutor
    ) : PopularResponseCache {
        return PopularResponseCache(appContext, serializer, fileManager, threadExecutor)
    }

    @Provides
    @Singleton
    internal fun provideBookDataStoreFactory(
            @Named(RepositoryQualifiers.BOOK_REPOSITORY)
            cache: PopularResponseCache,
            cloudPopular: CloudPopularBook,
            discPopular: DiskPopularBook
    ) : PopularBookDataStore {
        return PopularBookFactory(cache, cloudPopular, discPopular)
    }

    @Provides
    @Singleton
    @Named(RepositoryQualifiers.BOOK_REPOSITORY)
    internal fun provideCloudPopularBookDataStore(
            @Named(RepositoryQualifiers.BOOK_REPOSITORY)
            cache: PopularResponseCache,
            @Named(NetworkQualifiers.RSS_ITUNES_API)
            api: RssItunesApi
    ) =  CloudPopularBook(api, cache)

    @Provides
    @Singleton
    internal fun provideDiscPopularBookDataStore(
            @Named(RepositoryQualifiers.BOOK_REPOSITORY)
            cache: PopularResponseCache
    ) = DiskPopularBook(cache)
}