package com.nimtego.plectrum.presentation.di.modules.navigation

import com.nimtego.plectrum.presentation.navigation.LocalCiceroneHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LocalNavigationModule {

    @Provides
    @Singleton
    internal fun provideLocalNavigationHolder(): LocalCiceroneHolder {
        return LocalCiceroneHolder()
    }

}