package com.nimtego.plectrum

import android.app.Application
import com.nimtego.plectrum.data.repository.repository.AppRepository
import com.nimtego.plectrum.domain.repository.Repository
import com.nimtego.plectrum.presentation.di.AppComponent
import com.nimtego.plectrum.presentation.di.DaggerAppComponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {

    private lateinit var appComponent: AppComponent
    private val repository: Repository? = null
    private lateinit var cicerone: Cicerone<Router>

    fun getRepository(): Repository {
        return repository ?: AppRepository()
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initCicerone()
        initAppComponent()
    }

    private fun initAppComponent() {
        this.appComponent = DaggerAppComponent.builder().build()
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    fun getAppComponent() = appComponent

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }

    fun getNavigatorHolder() = cicerone.getNavigatorHolder()

    fun getRouter() = cicerone.getRouter()
}