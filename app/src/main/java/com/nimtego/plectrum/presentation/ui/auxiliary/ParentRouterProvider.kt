package com.nimtego.plectrum.presentation.ui.auxiliary

import ru.terrakok.cicerone.Router

interface ParentRouterProvider {
    fun getParentRouter(): Router
}