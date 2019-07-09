package com.nimtego.plectrum

import android.app.Application
import com.nimtego.plectrum.presentation.di.components.ApplicationComponent
import com.nimtego.plectrum.presentation.di.components.DaggerApplicationComponent
import com.nimtego.plectrum.presentation.di.modules.ContextModule

class App : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initAppComponent()
    }

    private fun initAppComponent() {
        this.appComponent =
                DaggerApplicationComponent.builder().contextModule(ContextModule(this)).build()
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    fun getAppComponent() = appComponent
}