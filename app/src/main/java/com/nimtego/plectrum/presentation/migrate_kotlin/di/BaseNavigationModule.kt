package com.nimtego.plectrum.presentation.migrate_kotlin.di

import com.nimtego.plectrum.presentation.migrate_kotlin.presenters.DashboardPresenter
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import ru.terrakok.cicerone.Router


@Module
class BaseNavigationModule {

    @Provides
    @Singleton
    internal fun provideLocalNavigationHolder(): DashboardPresenter {
        //todo prepare
        return DashboardPresenter(Router(), 1)
    }
}