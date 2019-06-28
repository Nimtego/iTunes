package com.nimtego.plectrum.presentation.di.modules.presentation

import com.nimtego.plectrum.domain.interactor.DashBoardInteractor
import com.nimtego.plectrum.domain.interactor.MoreSectionInteractor
import com.nimtego.plectrum.domain.interactor.TabContentInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationModule
import com.nimtego.plectrum.presentation.di.modules.domain.InteractorModule
import com.nimtego.plectrum.presentation.old.main.MainPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.BottomNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.MoreSectionPresenter
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
                           interactor: DashBoardInteractor): BottomNavigationPresenter {
        return BottomNavigationPresenter(router, 1, interactor)
    }

    @Provides
    fun sectionMorePresenter(router: Router,
                           interactor: MoreSectionInteractor): MoreSectionPresenter {
        return MoreSectionPresenter(router, 1, interactor)
    }
}
