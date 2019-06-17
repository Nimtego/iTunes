package com.nimtego.plectrum.presentation.di.modules.presentation

import com.nimtego.plectrum.domain.interactor.DashBoardInteractor
import com.nimtego.plectrum.domain.interactor.TabContentInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationModule
import com.nimtego.plectrum.presentation.di.modules.domain.InteractorModule
import com.nimtego.plectrum.presentation.main.MainPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.DashBoardPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.TabContentPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module(includes = [InteractorModule::class, NavigationModule::class])
class PresenterModule {


    @Provides
    fun mainPresenter(): MainPresenter {
        return MainPresenter()
    }

    @Provides
    fun tabContentPresenter(router: Router,
                            interactor: TabContentInteractor): TabContentPresenter {
        return TabContentPresenter(router, 1, interactor)
    }

    @Provides
    fun dashBoardPresenter(router: Router,
                           interactor: DashBoardInteractor): DashBoardPresenter {
        return DashBoardPresenter(router, 1, interactor)
    }
}
