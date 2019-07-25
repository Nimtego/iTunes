package com.nimtego.plectrum.presentation.di.modules.presentation

import com.nimtego.plectrum.domain.interactor.*
import com.nimtego.plectrum.presentation.di.modules.domain.InteractorModule
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationModule
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.manger.MainChoiceItemStorage
import com.nimtego.plectrum.presentation.mvp.presenters.*
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Named

@Module(includes = [InteractorModule::class,
                    NavigationModule::class,
                    StorageModule::class])
class PresenterModule {

    @Provides
    fun splashPresenter(
            @Named(NavigationQualifiers.APP_NAVIGATION) appRouter: Router,
            interactor: AppLaunchInteractor
    ): SplashPresenter {
        return SplashPresenter(appRouter, interactor)
    }

    @Provides
    fun bottomBarPresenter(
            @Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION) bottomRouter: Router,
            @Named(NavigationQualifiers.APP_NAVIGATION) appRouter: Router
    ): BottomNavigationPresenter {
        return BottomNavigationPresenter(bottomRouter, appRouter)
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
            @Named(NavigationQualifiers.TAB_BOOK_NAVIGATION) router: Router,
            @Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION) bottomRouter: Router
    ): BookNavigationPresenter {
        return BookNavigationPresenter(router, bottomRouter)
    }

    @Provides
    fun musicTabPresenter(
            @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
            router: Router,
            @Named(NavigationQualifiers.APP_NAVIGATION)
            appRouter: Router,
            @Named(StorageQualifiers.MAIN_ITEM_STORAGE_MANAGER)
            itemStorage: MainChoiceItemStorage,
            interactor: PopularMusicInteractor
    ): MusicTabPresenter {
        return MusicTabPresenter(router, appRouter, itemStorage, interactor)
    }

    @Provides
    fun movieTabPresenter(
            @Named(NavigationQualifiers.TAB_MOVIE_NAVIGATION)
            router: Router,
            @Named(NavigationQualifiers.APP_NAVIGATION)
            appRouter: Router,
            @Named(StorageQualifiers.MAIN_ITEM_STORAGE_MANAGER)
            itemStorage: MainChoiceItemStorage,
            interactor: PopularMovieInteractor
    ): MovieTabPresenter {
        return MovieTabPresenter(router, appRouter, itemStorage, interactor)
    }

    @Provides
    fun bookTabPresenter(
            @Named(NavigationQualifiers.TAB_BOOK_NAVIGATION)
            router: Router,
            @Named(NavigationQualifiers.APP_NAVIGATION)
            appRouter: Router,
            @Named(StorageQualifiers.MAIN_ITEM_STORAGE_MANAGER)
            itemStorage: MainChoiceItemStorage,
            interactor: PopularBookInteractor
    ): BookTabPresenter {
        return BookTabPresenter(router, appRouter, itemStorage, interactor)
    }

    @Provides
    fun sectionMorePresenter(
            @Named(NavigationQualifiers.ROUTER_HANDLER)
            router: HashMap<String, Cicerone<Router>>,
            @Named(StorageQualifiers.MAIN_ITEM_STORAGE_MANAGER)
            itemStorage: MainChoiceItemStorage,
            interactor: MoreSectionInteractor
    ): MoreSectionPresenter {
        return MoreSectionPresenter(router, interactor, itemStorage)
    }

    //todo
    @Provides
    fun informationPresenter(
            @Named(NavigationQualifiers.ROUTER_HANDLER)
            router: HashMap<String, Cicerone<Router>>,
            @Named(StorageQualifiers.MAIN_ITEM_STORAGE_MANAGER)
            itemStorage: MainChoiceItemStorage,
            interactor: InformationInteractor
    ): InformationPresenter {
        return InformationPresenter(router, interactor, itemStorage)
    }


}
