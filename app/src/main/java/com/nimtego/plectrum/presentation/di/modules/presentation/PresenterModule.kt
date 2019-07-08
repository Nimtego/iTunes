package com.nimtego.plectrum.presentation.di.modules.presentation

import com.nimtego.plectrum.domain.interactor.MoreSectionInteractor
import com.nimtego.plectrum.domain.interactor.TabContentInteractor
import com.nimtego.plectrum.presentation.di.modules.domain.InteractorModule
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationModule
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.*
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router
import javax.inject.Named

@Module(includes = [InteractorModule::class, NavigationModule::class])
class PresenterModule {

    @Provides
    fun tabContentPresenter(
            @Named(NavigationQualifiers.TAB_CONTENT_NAVIGATION) router: Router,
            interactor: TabContentInteractor
    ): TabContentPresenter {
        return TabContentPresenter(router, interactor)
    }

    @Provides
    fun bottomBarPresenter(): BottomNavigationPresenter {
        return BottomNavigationPresenter()
    }

    @Provides
    fun sectionMorePresenter(
            @Named(NavigationQualifiers.MORE_SECTION_NAVIGATION) router: Router,
            interactor: MoreSectionInteractor
    ): MoreSectionPresenter {
        return MoreSectionPresenter(router, interactor)
    }

    //todo
    @Provides
    fun musicTabNavPresenter(
            @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION) router: Router,
            @Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION) bottomRouter: Router
    ): MusicNavigationPresenter {
        return MusicNavigationPresenter(router, bottomRouter)
    }

    @Provides
    fun movieTabNavPresenter(
            @Named(NavigationQualifiers.TAB_MOVIE_NAVIGATION) router: Router,
            @Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION) bottomRouter: Router
    ): MovieNavigationPresenter {
        return MovieNavigationPresenter(router, bottomRouter)
    }

    @Provides
    fun bookTabNavPresenter(
            @Named(NavigationQualifiers.TAB_MOVIE_NAVIGATION) router: Router,
            @Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION) bottomRouter: Router
    ): BookNavigationPresenter {
        return BookNavigationPresenter(router, bottomRouter)
    }

    @Provides
    fun musicTabPresenter(
            @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION) router: Router,
            @Named(NavigationQualifiers.APP_NAVIGATION) appRouter: Router,
            interactor: TabContentInteractor
    ): MusicTabPresenter {
        return MusicTabPresenter(router, appRouter, interactor)
    }

    @Provides
    fun movieTabPresenter(
            @Named(NavigationQualifiers.TAB_MOVIE_NAVIGATION) router: Router,
            @Named(NavigationQualifiers.APP_NAVIGATION) appRouter: Router,
            interactor: TabContentInteractor
    ): MovieTabPresenter {
        return MovieTabPresenter(router, appRouter, interactor)
    }

    @Provides
    fun bookTabPresenter(
            @Named(NavigationQualifiers.TAB_BOOK_NAVIGATION) router: Router,
            @Named(NavigationQualifiers.APP_NAVIGATION) appRouter: Router,
            interactor: TabContentInteractor
    ): BookTabPresenter {
        return BookTabPresenter(router, appRouter, interactor)
    }
}
