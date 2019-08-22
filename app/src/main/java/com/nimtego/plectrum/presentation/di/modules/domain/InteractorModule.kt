package com.nimtego.plectrum.presentation.di.modules.domain

import com.nimtego.plectrum.data.repository.repository.*
import com.nimtego.plectrum.domain.interactor.*
import com.nimtego.plectrum.domain.interactor.general.MoreSectionInteractor
import com.nimtego.plectrum.domain.interactor.popular.PopularBookInteractor
import com.nimtego.plectrum.domain.interactor.popular.PopularMovieInteractor
import com.nimtego.plectrum.domain.interactor.popular.PopularMusicInteractor
import com.nimtego.plectrum.domain.interactor.search.SearchMusicContentInteractor
import com.nimtego.plectrum.presentation.di.modules.data.RepositoryModule
import com.nimtego.plectrum.presentation.interactor.LaunchUseCase
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.MusicalSearchUseCase
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
            popularMusicRepository: PopularMusicRepository,
            popularMovieRepository: PopularMovieRepository,
            popularBookRepository: PopularBookRepository,
            schedulersProvider: SchedulersProvider
    ): LaunchUseCase {
        return AppLaunchInteractor(popularMusicRepository = popularMusicRepository,
                popularMovieRepository = popularMovieRepository,
                popularBookRepository = popularBookRepository,
                schedulersProvider = schedulersProvider)
    }

    @Provides
    @Singleton
    internal fun providePopularMusicInteractor(
            repository: PopularMusicRepository,
            compositeDisposable: CompositeDisposable
    ) = PopularMusicInteractor(compositeDisposable, repository)

    @Provides
    @Singleton
    internal fun providePopularMovieInteractor(
            repository: PopularMovieRepository,
            compositeDisposable: CompositeDisposable
    ) = PopularMovieInteractor(compositeDisposable, repository)

    @Provides
    @Singleton
    internal fun providePopularBookInteractor(
            repository: PopularBookRepository,
            compositeDisposable: CompositeDisposable
    ) = PopularBookInteractor(compositeDisposable, repository)

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
}
