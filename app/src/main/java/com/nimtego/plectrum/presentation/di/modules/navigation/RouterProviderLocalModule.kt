package com.nimtego.plectrum.presentation.di.modules.navigation

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class RouterProviderLocalModule internal constructor(parentRouter: Router) {

    private val parentRouter: Router

    init {
        this.parentRouter = parentRouter
    }

    @Provides
    fun router(): Router {
        return parentRouter
    }
}