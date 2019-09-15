package com.nimtego.plectrum.presentation.ui.common

import ru.terrakok.cicerone.Router

interface ParentRouterProvider {
    fun getParentRouter(): Router
}