package com.nimtego.plectrum.presentation.mvp.presenters

import ru.terrakok.cicerone.Router

interface RouterProvider {
    fun getRouter(): Router
}