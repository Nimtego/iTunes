package com.nimtego.plectrum.presentation.migrate_kotlin

import android.app.Application
import com.nimtego.plectrum.presentation.migrate_kotlin.di.AppComponent
import com.nimtego.plectrum.presentation.migrate_kotlin.di.DaggerAppComponent

class App : Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    fun getAppComponent(): AppComponent? {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().build()
        }
        return appComponent
    }
}