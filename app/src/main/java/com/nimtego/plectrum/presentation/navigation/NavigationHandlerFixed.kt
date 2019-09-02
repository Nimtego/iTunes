package com.nimtego.plectrum.presentation.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import java.lang.IllegalArgumentException
import javax.inject.Inject

class NavigationHandlerFixed @Inject constructor(
        private val containers: Map<String, Cicerone<Router>>
) : NavigationHandler {

    private fun getCicerone(qualifier: String): Cicerone<Router> {
        return containers[qualifier] ?:
        throw IllegalArgumentException("$qualifier in ${this.javaClass.canonicalName} not found")
    }

    override fun getNavigatorHolder(qualifier: String): NavigatorHolder {
        return getCicerone(qualifier).navigatorHolder
    }

    override fun getRouter(qualifier: String): Router {
        return getCicerone(qualifier).router
    }
}