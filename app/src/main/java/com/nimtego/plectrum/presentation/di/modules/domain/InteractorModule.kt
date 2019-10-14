package com.nimtego.plectrum.presentation.di.modules.domain

import com.nimtego.plectrum.data.repository.repository.*
import com.nimtego.plectrum.domain.interactor.*
import com.nimtego.plectrum.domain.interactor.detail.AlbumDetail
import com.nimtego.plectrum.domain.interactor.detail.ArtistDetail
import com.nimtego.plectrum.domain.interactor.detail.SongDetail
import com.nimtego.plectrum.domain.interactor.general.MoreSectionInteractor
import com.nimtego.plectrum.domain.interactor.popular.PopularBookInteractor
import com.nimtego.plectrum.domain.interactor.popular.PopularMovieInteractor
import com.nimtego.plectrum.domain.interactor.popular.PopularMusicInteractor
import com.nimtego.plectrum.domain.interactor.search.SearchMusicContentInteractor
import com.nimtego.plectrum.domain.repository.detail.MusicalDetailRepository
import com.nimtego.plectrum.presentation.di.modules.data.RepositoryModule
import com.nimtego.plectrum.presentation.interactor.LaunchUseCase
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.MusicalSearchUseCase
import com.nimtego.plectrum.presentation.interactor.detail.AlbumDetailUseCase
import com.nimtego.plectrum.presentation.interactor.detail.ArtistDetailUseCase
import com.nimtego.plectrum.presentation.interactor.detail.SongDetailUseCase
import com.nimtego.plectrum.presentation.mvp.model.song.Album
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class InteractorModule {

    @Provides
    internal fun compositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    internal fun provideAppLaunchnInteractor(
            musicRepository: PopularMusicRepository,
            movieRepository: PopularMovieRepository,
            bookRepository: PopularBookRepository,
            schedulersProvider: SchedulersProvider
    ): LaunchUseCase {
        return AppLaunchInteractor(musicRepository = musicRepository,
                movieRepository = movieRepository,
                bookRepository = bookRepository,
                schedulersProvider = schedulersProvider)
    }

    @Provides
    @Singleton
    internal fun providePopularMusicInteractor(
            repositoryMusicRepository: PopularMusicRepository,
            compositeDisposable: CompositeDisposable
    ) = PopularMusicInteractor(compositeDisposable, repositoryMusicRepository)

    @Provides
    @Singleton
    internal fun providePopularMovieInteractor(
            repositoryMovieRepository: PopularMovieRepository,
            compositeDisposable: CompositeDisposable
    ) = PopularMovieInteractor(compositeDisposable, repositoryMovieRepository)

    @Provides
    @Singleton
    internal fun providePopularBookInteractor(
            repositoryBookRepository: PopularBookRepository,
            compositeDisposable: CompositeDisposable
    ) = PopularBookInteractor(compositeDisposable, repositoryBookRepository)

    @Provides
    @Singleton
    internal fun provideMoreSectionInteractor(
            repository: MoreSectionRepository,
            compositeDisposable: CompositeDisposable
    ) = MoreSectionInteractor(compositeDisposable, repository)

    @Provides
    @Singleton
    internal fun provideSearchInteractor(
            schedulersProvider: SchedulersProvider,
            repository: MusicalRepository
    ): MusicalSearchUseCase {
        return SearchMusicContentInteractor(schedulersProvider, repository)
    }

    @Provides
    @Singleton
    internal fun provideSongDetailInteractor(
            schedulersProvider: SchedulersProvider,
            repository: MusicalDetailRepository
    ): SongDetailUseCase {
        return SongDetail(schedulersProvider, repository)
    }

    @Provides
    @Singleton
    internal fun provideAlbumDetailInteractor(
            schedulersProvider: SchedulersProvider,
            repository: MusicalDetailRepository
    ): AlbumDetailUseCase {
        return AlbumDetail(schedulersProvider, repository)
    }

    @Provides
    @Singleton
    internal fun provideArtistDetailInteractor(
            schedulersProvider: SchedulersProvider,
            repository: MusicalDetailRepository
    ): ArtistDetailUseCase {
        return ArtistDetail(schedulersProvider, repository)
    }
}
