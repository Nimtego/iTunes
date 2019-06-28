package com.nimtego.plectrum

import android.app.Application
import com.nimtego.plectrum.data.repository.repository.AppRepository
import com.nimtego.plectrum.domain.repository.Repository
import com.nimtego.plectrum.presentation.di.components.ApplicationComponent
import com.nimtego.plectrum.presentation.di.components.DaggerApplicationComponent
import com.nimtego.plectrum.presentation.di.modules.ContextModule
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class App : Application() {

    private lateinit var appComponent: ApplicationComponent
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
        this.appComponent =
                DaggerApplicationComponent.builder().contextModule(ContextModule(this)).build()
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    fun getAppComponent() = appComponent

    private fun initCicerone() {
        this.cicerone = Cicerone.create()
    }

    fun getNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    fun getRouter() = cicerone.router
}