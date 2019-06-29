package com.nimtego.plectrum.presentation.di.modules

import dagger.Provides
import android.app.Activity
import android.content.Context
import dagger.Module


@Module
class ActivityModule internal constructor(context: Activity) {

    private val context: Context

    init {
        this.context = context
    }

    @Provides
    fun context(): Context {
        return context
    }
}