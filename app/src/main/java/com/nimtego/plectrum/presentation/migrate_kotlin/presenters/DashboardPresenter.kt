package com.nimtego.plectrum.presentation.migrate_kotlin.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.migrate_kotlin.mvp.DashboardView
import ru.terrakok.cicerone.Router
import com.nimtego.plectrum.presentation.migrate_kotlin.Screens


@InjectViewState
class DashboardPresenter(private val router: Router?,
                         private val screenNumber: Int)
    : BasePresenter<DashboardView>(router, screenNumber) {
}