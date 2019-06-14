package com.nimtego.plectrum.presentation.di

import com.nimtego.plectrum.presentation.di.modules.presentation.PresenterModule
import com.nimtego.plectrum.presentation.ui.activity.MainActivity
import com.nimtego.plectrum.presentation.ui.fragment.TabContentFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [PresenterModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: TabContentFragment)
//
//    fun inject(activity: BottomNavigationActivity)
//
//    fun inject(fragment: TabContainerFragment)
//
//    fun inject(fragment: ProfileFragment)
//
//    fun inject(fragment: SelectPhotoFragment)
//
//    fun inject(activity: ProfileActivity)
}