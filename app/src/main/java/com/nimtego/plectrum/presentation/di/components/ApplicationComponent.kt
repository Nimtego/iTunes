package com.nimtego.plectrum.presentation.di.components

import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationModule
import com.nimtego.plectrum.presentation.di.modules.presentation.PresenterModule
import com.nimtego.plectrum.presentation.di.modules.system.SystemModule
import com.nimtego.plectrum.presentation.ui.activity.AppActivity
import com.nimtego.plectrum.presentation.ui.fragment.detail.AlbumDetailFragment
import com.nimtego.plectrum.presentation.ui.fragment.detail.ArtistDetailFragment
import com.nimtego.plectrum.presentation.ui.fragment.detail.SongDetailFragment
import com.nimtego.plectrum.presentation.ui.fragment.popular.InformationFragment
import com.nimtego.plectrum.presentation.ui.fragment.popular.MoreSectionFragment
import com.nimtego.plectrum.presentation.ui.fragment.general.SplashFragment
import com.nimtego.plectrum.presentation.ui.fragment.general.TrackInformationFragment
import com.nimtego.plectrum.presentation.ui.fragment.navigation.*
import com.nimtego.plectrum.presentation.ui.fragment.popular.BookTabFragment
import com.nimtego.plectrum.presentation.ui.fragment.popular.MovieTabFragment
import com.nimtego.plectrum.presentation.ui.fragment.popular.MusicTabFragment
import com.nimtego.plectrum.presentation.ui.fragment.search.SearchContentFragment
import com.nimtego.plectrum.presentation.ui.fragment.search.SearchInformationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterModule::class,
                      SystemModule::class,
                      NavigationModule::class])
interface ApplicationComponent {

    fun inject(activity: AppActivity)

    fun inject(fragment: SplashFragment)

    fun inject(fragment: BottomNavigationFragment)

    fun inject(fragment: MusicTabNavFragment)

    fun inject(fragment: MovieTabNavFragment)

    fun inject(fragment: BookTabNavFragment)

    fun inject(fragment: MusicTabFragment)

    fun inject(fragment: MovieTabFragment)

    fun inject(fragment: BookTabFragment)

    fun inject(fragment: MoreSectionFragment)

    fun inject(fragment: InformationFragment)

    fun inject(fragment: TrackInformationFragment)

    fun inject(fragment: SearchNavigationFragment)

    fun inject(fragment: SearchTabNavFragment)

    fun inject(fragment: SearchContentFragment)

    fun inject(fragment: SearchInformationFragment)

    fun inject(fragment: AlbumDetailFragment)

    fun inject(fragment: ArtistDetailFragment)

    fun inject(fragment: SongDetailFragment)
}