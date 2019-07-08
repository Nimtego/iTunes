package com.nimtego.plectrum.presentation.di.components

import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationModule
import com.nimtego.plectrum.presentation.di.modules.presentation.PresenterModule
import com.nimtego.plectrum.presentation.di.modules.system.SystemModule
import com.nimtego.plectrum.presentation.ui.activity.AppActivity
import com.nimtego.plectrum.presentation.ui.fragment.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterModule::class,
                      SystemModule::class,
                      NavigationModule::class])
interface ApplicationComponent {

    fun inject(activity: AppActivity)

    fun inject(fragment: BottomNavigationFragment)

    fun inject(fragment: MusicTabNavFragment)

    fun inject(fragment: MovieTabNavFragment)

    fun inject(fragment: BookTabNavFragment)

    fun inject(fragment: MusicTabFragment)

    fun inject(fragment: MovieTabFragment)

    fun inject(fragment: BookTabFragment)

    fun inject(fragment: TabContentFragment)

    fun inject(fragment: MoreSectionFragment)

}