package com.nimtego.plectrum.presentation.di.modules.navigation

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Named
import javax.inject.Singleton


@Module
class NavigationModule {

    private val appCicerone: Cicerone<Router> = Cicerone.create()
    private val bottomNavigationBarCicerone: Cicerone<Router> = Cicerone.create()
    private val tabContentNavigationBarCicerone: Cicerone<Router> = Cicerone.create()
    private val musicTabNavigationCicerone: Cicerone<Router> = Cicerone.create()
    private val movieTabNavigationCicerone: Cicerone<Router> = Cicerone.create()
    private val bookTabNavigationCicerone: Cicerone<Router> = Cicerone.create()

    private val searchMusicNavigationCicerone: Cicerone<Router> = Cicerone.create()


    private val routerHandler: HashMap<String, Cicerone<Router>> =
            hashMapOf(
                    NavigationQualifiers.APP_NAVIGATION to appCicerone,
                    NavigationQualifiers.BOTTOM_BAR_NAVIGATION to  bottomNavigationBarCicerone,
                    NavigationQualifiers.TAB_MUSIC_NAVIGATION to  musicTabNavigationCicerone,
                    NavigationQualifiers.TAB_MOVIE_NAVIGATION to  movieTabNavigationCicerone,
                    NavigationQualifiers.TAB_BOOK_NAVIGATION to  bookTabNavigationCicerone)

    private val searchRouterHandler: HashMap<String, Cicerone<Router>> =
            hashMapOf(
                    NavigationQualifiers.SEARCH_MUSIC_NAVIGATION to searchMusicNavigationCicerone)

    @Provides
    @Singleton
    @Named(NavigationQualifiers.APP_NAVIGATION)
    internal fun provideAppRouter(): Router {
        return appCicerone.router
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.APP_NAVIGATION)
    internal fun provideAppNavigatorHolder(): NavigatorHolder {
        return appCicerone.navigatorHolder
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)
    internal fun provideBottomNavigationRouter(): Router {
        return bottomNavigationBarCicerone.router
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)
    internal fun provideBottomNavigationNavigatorHolder(): NavigatorHolder {
        return bottomNavigationBarCicerone.navigatorHolder
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
    internal fun provideTabMusicRouter(): Router {
        return musicTabNavigationCicerone.router
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)
    internal fun provideTabMusicNavigatorHolder(): NavigatorHolder {
        return musicTabNavigationCicerone.navigatorHolder
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.TAB_MOVIE_NAVIGATION)
    internal fun provideTabMovieRouter(): Router {
        return movieTabNavigationCicerone.router
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.TAB_MOVIE_NAVIGATION)
    internal fun provideTabMovieNavigatorHolder(): NavigatorHolder {
        return movieTabNavigationCicerone.navigatorHolder
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.TAB_BOOK_NAVIGATION)
    internal fun provideTabBookRouter(): Router {
        return bookTabNavigationCicerone.router
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.TAB_BOOK_NAVIGATION)
    internal fun provideTabBookNavigatorHolder(): NavigatorHolder {
        return bookTabNavigationCicerone.navigatorHolder
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.TAB_CONTENT_NAVIGATION)
    internal fun provideTabContentRouter(): Router {
        return tabContentNavigationBarCicerone.router
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.TAB_CONTENT_NAVIGATION)
    internal fun provideTabContentNavigatorHolder(): NavigatorHolder {
        return tabContentNavigationBarCicerone.navigatorHolder
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.ROUTER_HANDLER)
    internal fun provideRouterHandler(): HashMap<String, Cicerone<Router>> {
        return routerHandler
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.SEARCH_MUSIC_NAVIGATION)
    internal fun provideSearchRouterHandler(): HashMap<String, Cicerone<Router>> {
        return searchRouterHandler
    }


    @Provides
    @Singleton
    @Named(NavigationQualifiers.SEARCH_MUSIC_NAVIGATION)
    internal fun provideSearchMusicRouter(): Router {
        return searchMusicNavigationCicerone.router
    }

    @Provides
    @Singleton
    @Named(NavigationQualifiers.SEARCH_MUSIC_NAVIGATION)
    internal fun provideSearchMusicNavigatorHolder(): NavigatorHolder {
        return searchMusicNavigationCicerone.navigatorHolder
    }

//    @Provides
//    @Singleton
//    @Named(NavigationQualifiers.MORE_SECTION_NAVIGATION)
//    internal fun provideMoreSongContentNavigatorHolder(): NavigatorHolder {
//        return musicTabNavigationCicerone.navigatorHolder
//    }

}