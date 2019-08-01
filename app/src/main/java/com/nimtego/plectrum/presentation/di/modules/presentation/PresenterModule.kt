package com.nimtego.plectrum.presentation.di.modules.presentation

import com.nimtego.plectrum.domain.interactor.*
import com.nimtego.plectrum.presentation.di.modules.domain.InteractorModule
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationModule
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.interactor.LaunchUseCase
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.manger.MainChoiceItemStorage
import com.nimtego.plectrum.presentation.manger.MusicalItemStorage
import com.nimtego.plectrum.presentation.manger.MusicalItemStorageImp
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
            interactor: LaunchUseCase,
            schedulersProvider: SchedulersProvider
    ): SplashPresenter {
        return SplashPresenter(appRouter, interactor, schedulersProvider)
    }

    @Provides
    fun bottomBarPresenter(
            @Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION) bottomRouter: Router,
            @Named(NavigationQualifiers.APP_NAVIGATION) appRouter: Router
    ): BottomNavigationPresenter {
        return BottomNavigationPresenter(bottomRouter, appRouter)
    }

    @Provides
    @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
    fun musicTabNavPresenter(
            @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION) router: Router
    ): TabNavigationPresenter {
        return TabNavigationPresenter(router)
    }

    @Provides
    @Named(NavigationQualifiers.TAB_MOVIE_NAVIGATION)
    fun movieTabNavPresenter(
            @Named(NavigationQualifiers.TAB_MOVIE_NAVIGATION) router: Router
    ): TabNavigationPresenter {
        return TabNavigationPresenter(router)
    }

    @Provides
    @Named(NavigationQualifiers.TAB_BOOK_NAVIGATION)
    fun bookTabNavPresenter(
            @Named(NavigationQualifiers.TAB_BOOK_NAVIGATION) router: Router
    ): TabNavigationPresenter {
        return TabNavigationPresenter(router)
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

    @Provides
    fun trackInformationPresenter(
            @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
            router: Router,
            itemStorage: MusicalItemStorageImp,
            interactor: TrackInformationInteractor
    ): TrackInformationPresenter {
        return TrackInformationPresenter(router, itemStorage, interactor)
    }


}
