package com.nimtego.plectrum.presentation.di.modules.presentation

import com.nimtego.plectrum.domain.interactor.general.InformationInteractor
import com.nimtego.plectrum.domain.interactor.general.MoreSectionInteractor
import com.nimtego.plectrum.domain.interactor.general.TrackInformationInteractor
import com.nimtego.plectrum.domain.interactor.popular.PopularBookInteractor
import com.nimtego.plectrum.domain.interactor.popular.PopularMovieInteractor
import com.nimtego.plectrum.domain.interactor.popular.PopularMusicInteractor
import com.nimtego.plectrum.presentation.di.modules.domain.InteractorModule
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationModule
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.interactor.LaunchUseCase
import com.nimtego.plectrum.presentation.interactor.SchedulersProvider
import com.nimtego.plectrum.presentation.interactor.MusicalSearchUseCase
import com.nimtego.plectrum.presentation.manger.*
import com.nimtego.plectrum.presentation.mvp.presenters.general.InformationPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.general.MoreSectionPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.general.SplashPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.general.TrackInformationPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.navigation.BottomNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.navigation.TabNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.popular.BookTabPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.popular.MovieTabPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.popular.MusicTabPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.search.SearchContentPresenter
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
            userSearchItemStorage: UserSearchItemStorage
    ): BottomNavigationPresenter {
        return BottomNavigationPresenter(bottomRouter, userSearchItemStorage)
    }

    @Provides
    @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
    fun musicTabNavPresenter(
            @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION) router: Router,
            userSearchItemStorage: UserSearchItemStorage
    ): TabNavigationPresenter {
        return TabNavigationPresenter(router, userSearchItemStorage)
    }

    @Provides
    @Named(NavigationQualifiers.TAB_MOVIE_NAVIGATION)
    fun movieTabNavPresenter(
            @Named(NavigationQualifiers.TAB_MOVIE_NAVIGATION) router: Router,
            userSearchItemStorage: UserSearchItemStorage
    ): TabNavigationPresenter {
        return TabNavigationPresenter(router, userSearchItemStorage)
    }

    @Provides
    @Named(NavigationQualifiers.TAB_BOOK_NAVIGATION)
    fun bookTabNavPresenter(
            @Named(NavigationQualifiers.TAB_BOOK_NAVIGATION) router: Router,
            userSearchItemStorage: UserSearchItemStorage
    ): TabNavigationPresenter {
        return TabNavigationPresenter(router, userSearchItemStorage)
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

    @Provides
    fun searchPresenter(
            @Named(NavigationQualifiers.ROUTER_HANDLER)
            router: HashMap<String, Cicerone<Router>>,
            interactor: MusicalSearchUseCase,
            itemStorage: UserSearchItemStorage,
            schedulersProvider: SchedulersProvider
    ): SearchContentPresenter {
        return SearchContentPresenter(router, interactor, itemStorage, schedulersProvider)
    }
}
