package com.nimtego.plectrum.presentation.di.components

import com.nimtego.plectrum.presentation.di.modules.navigation.LocalNavigationModule
import com.nimtego.plectrum.presentation.di.modules.presentation.PresenterModule
import com.nimtego.plectrum.presentation.ui.activity.MainActivity
import com.nimtego.plectrum.presentation.ui.fragment.BottomNavigationFragment
import com.nimtego.plectrum.presentation.ui.fragment.MoreSectionFragment
import com.nimtego.plectrum.presentation.ui.fragment.TabContentFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterModule::class, LocalNavigationModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: BottomNavigationFragment)

    fun inject(fragment: TabContentFragment)

    fun inject(fragment: MoreSectionFragment)

}