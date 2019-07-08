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

}