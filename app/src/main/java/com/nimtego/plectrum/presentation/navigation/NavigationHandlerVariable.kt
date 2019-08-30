package com.nimtego.plectrum.presentation.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class NavigationHandlerVariable @Inject constructor() : NavigationHandler {

    private val containers: HashMap<String, Cicerone<Router>> = HashMap()

    private fun getCicerone(qualifier: String): Cicerone<Router> {
        if (!containers.containsKey(qualifier)) {
            containers[qualifier] = Cicerone.create()
        }
        return containers[qualifier]!!
    }

    override fun getNavigatorHolder(qualifier: String): NavigatorHolder {
        return getCicerone(qualifier).navigatorHolder
    }

    override fun getRouter(qualifier: String): Router {
        return getCicerone(qualifier).router
    }
}