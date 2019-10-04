package com.nimtego.plectrum.presentation.di.modules.data

import com.nimtego.plectrum.data.model.mappers.MusicalContentMapper
import com.nimtego.plectrum.data.model.mappers.PopularBookMapper
import com.nimtego.plectrum.data.model.mappers.PopularMovieMapper
import com.nimtego.plectrum.data.model.mappers.PopularMusicMapper
import com.nimtego.plectrum.data.repository.datasource.popular.book.PopularBookFactory
import com.nimtego.plectrum.data.repository.datasource.popular.movie.PopularMovieFactory
import com.nimtego.plectrum.data.repository.datasource.popular.music.PopularMusicFactory
import com.nimtego.plectrum.data.repository.datasource.search.SongDataStoreFactory
import com.nimtego.plectrum.data.repository.repository.*
import com.nimtego.plectrum.domain.repository.RepositoryPopular
import com.nimtego.plectrum.presentation.di.modules.ContextModule
import com.nimtego.plectrum.presentation.di.modules.domain.RepositoryQualifiers
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ContextModule::class, DataStoreModule::class])
class RepositoryModule {


    @Provides
    @Singleton
    @Named(RepositoryQualifiers.MUSIC_REPOSITORY)
    internal fun providePopularMusicRepository(
            mapper: PopularMusicMapper,
            @Named(RepositoryQualifiers.MUSIC_REPOSITORY)
            dataStoreFactory: PopularMusicFactory
    ) : RepositoryPopular<BaseParentViewModel<ChildViewModel>> {
        return PopularMusicRepository(dataStoreFactory, mapper)
    }

    @Provides
    @Singleton
    @Named(RepositoryQualifiers.MUSIC_REPOSITORY)
    internal fun providePopularMovieRepository(
            mapper: PopularMovieMapper,
            @Named(RepositoryQualifiers.MOVIE_REPOSITORY)
            dataStoreFactory: PopularMovieFactory
    ) : RepositoryPopular<BaseParentViewModel<ChildViewModel>> {
        return PopularMovieRepository(dataStoreFactory, mapper)
    }

    @Provides
    @Singleton
    @Named(RepositoryQualifiers.MUSIC_REPOSITORY)
    internal fun providePopularBookRepository(
            mapper: PopularBookMapper,
            @Named(RepositoryQualifiers.MOVIE_REPOSITORY)
            dataStoreFactory: PopularBookFactory
    ) : RepositoryPopular<BaseParentViewModel<ChildViewModel>> {
        return PopularBookRepository(dataStoreFactory, mapper)
    }

    @Provides
    @Singleton
    @Named(RepositoryQualifiers.MUSIC_REPOSITORY)
    internal fun provideSongRepository(
            mapper: MusicalContentMapper,
            @Named(RepositoryQualifiers.MOVIE_REPOSITORY)
            dataStoreFactory: SongDataStoreFactory
    ) : SongRepository {
        return SongRepository(dataStoreFactory, mapper)
    }


    @Provides
    @Singleton
    internal fun provideMoreSectionRepository(mapper: PopularMusicMapper,
                                              musicDataStoreFactory: PopularMusicFactory,
                                              movieDataStoreFactory: PopularMovieFactory,
                                              bookDataStoreFactory: PopularBookFactory) =
            MoreSectionRepository(musicDataStoreFactory = musicDataStoreFactory,
                                  movieDataStoreFactory = movieDataStoreFactory,
                                  bookDataStoreFactory = bookDataStoreFactory,
                                  mapper = mapper)

    @Provides
    @Singleton
    internal fun provideSearchRepository(mapper: MusicalContentMapper,
                                              dataStoreFactory: SongDataStoreFactory
    ): MusicalRepository {
        return MusicalRepository(dataStoreFactory, mapper)
    }


    @Provides
    internal fun providePopularMusicMapper(): PopularMusicMapper {
        return PopularMusicMapper()
    }

    @Provides
    internal fun provideSearchMusicMapper(): MusicalContentMapper {
        return MusicalContentMapper()
    }

    @Provides
    internal fun providePopularMovieMapper(): PopularMovieMapper {
        return PopularMovieMapper()
    }

    @Provides
    internal fun providePopularBookMapper(): PopularBookMapper {
        return PopularBookMapper()
    }

}