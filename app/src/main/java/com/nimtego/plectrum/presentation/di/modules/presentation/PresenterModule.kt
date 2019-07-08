package com.nimtego.plectrum.presentation.di.modules.presentation

import com.nimtego.plectrum.domain.interactor.BottomNavigationInteractor
import com.nimtego.plectrum.domain.interactor.MoreSectionInteractor
import com.nimtego.plectrum.domain.interactor.TabContentInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationModule
import com.nimtego.plectrum.presentation.di.modules.domain.InteractorModule
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.BottomNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.MoreSectionPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.MusicNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.TabContentPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router
import javax.inject.Named

@Module(includes = [InteractorModule::class, NavigationModule::class])
class PresenterModule {

    @Provides
    fun tabContentPresenter(@Named(NavigationQualifiers.TAB_CONTENT_NAVIGATION)router: Router,
                            interactor: TabContentInteractor): TabContentPresenter {
        return TabContentPresenter(router, 1, interactor)
    }

    @Provides
    fun bottomBarPresenter(@Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)router: Router,
                           interactor: BottomNavigationInteractor): BottomNavigationPresenter {
        return BottomNavigationPresenter(router, 1, interactor)
    }

    @Provides
    fun sectionMorePresenter(@Named(NavigationQualifiers.MORE_SECTION_NAVIGATION)router: Router,
                           interactor: MoreSectionInteractor): MoreSectionPresenter {
        return MoreSectionPresenter(router, 1, interactor)
    }

    //todo
    @Provides
    fun musicTabNavPresenter(@Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)router: Router,
                             interactor: BottomNavigationInteractor): MusicNavigationPresenter {
        return MusicNavigationPresenter(router, 1, interactor)
    }
}
