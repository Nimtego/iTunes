package com.nimtego.plectrum.presentation.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class ContextModule(internal var context: Context) {

    @Provides
    fun context(): Context {
        return context.applicationContext
    }
}