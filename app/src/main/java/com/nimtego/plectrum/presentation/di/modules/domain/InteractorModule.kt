package com.nimtego.plectrum.presentation.di.modules.domain

import com.nimtego.plectrum.data.repository.repository.*
import com.nimtego.plectrum.domain.interactor.*
import com.nimtego.plectrum.presentation.di.modules.data.RepositoryModule
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class InteractorModule {

    @Provides
    @Singleton
    internal fun provideBottomNavigationInteractor(
            repository: DashBoardRepository,
            compositeDisposable: CompositeDisposable
    ) = BottomNavigationInteractor(compositeDisposable, repository)

    @Provides
    @Singleton
    internal fun provideTabContentInteractor(
            repository: TabContentRepository,
            compositeDisposable: CompositeDisposable
    ) = TabContentInteractor(compositeDisposable, repository)

    @Provides
    @Singleton
    internal fun provideMoreSectionInteractor(
            repository: MoreSectionRepository,
            compositeDisposable: CompositeDisposable
    ) = MoreSectionInteractor(compositeDisposable, repository)

    @Provides
    @Singleton
    internal fun provideInformationInteractor(
            repository: InformationRepository,
            compositeDisposable: CompositeDisposable
    ) = InformationInteractor(compositeDisposable, repository)

    @Provides
    @Singleton
    internal fun provideAlbumInteractor(
            repository: AlbumRepository,
            compositeDisposable: CompositeDisposable
    ) = AlbumInteractor(compositeDisposable, repository)
}
