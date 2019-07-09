package com.nimtego.plectrum.presentation.di.modules.domain

import com.nimtego.plectrum.data.repository.repository.AlbumRepository
import com.nimtego.plectrum.data.repository.repository.DashBoardRepository
import com.nimtego.plectrum.data.repository.repository.MoreSectionRepository
import com.nimtego.plectrum.data.repository.repository.TabContentRepository
import com.nimtego.plectrum.domain.interactor.AlbumInteractor
import com.nimtego.plectrum.domain.interactor.BottomNavigationInteractor
import com.nimtego.plectrum.domain.interactor.MoreSectionInteractor
import com.nimtego.plectrum.domain.interactor.TabContentInteractor
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
    internal fun provideAlbumInteractor(
            repository: AlbumRepository,
            compositeDisposable: CompositeDisposable
    ) = AlbumInteractor(compositeDisposable, repository)
}
