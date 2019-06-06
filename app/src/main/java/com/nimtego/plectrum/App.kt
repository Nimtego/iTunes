package com.nimtego.plectrum

import android.app.Application
import com.nimtego.plectrum.data.repository.AppRepository
import com.nimtego.plectrum.domain.Repository
import com.nimtego.plectrum.presentation.di.components.PresenterComponent
import com.nimtego.plectrum.presentation.di.AppComponent
import com.nimtego.plectrum.presentation.di.DaggerAppComponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class App : Application() {

    private var appComponent: AppComponent? = null
    private val repository: Repository? = null
    private var presenterComponent: PresenterComponent? = null
    private lateinit var cicerone: Cicerone<Router>

    fun getRepository(): Repository {
        return repository ?: AppRepository()
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initCicerone()
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

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }

    fun getNavigatorHolder(): NavigatorHolder? {
        return cicerone.getNavigatorHolder()
    }

    fun getRouter(): Router {
        return cicerone.getRouter()
    }
}