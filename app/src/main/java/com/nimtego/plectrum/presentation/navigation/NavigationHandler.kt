package com.nimtego.plectrum.presentation.navigation

import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

interface NavigationHandler {
    fun getNavigatorHolder(qualifier: String): NavigatorHolder
    fun getRouter(qualifier: String): Router
}