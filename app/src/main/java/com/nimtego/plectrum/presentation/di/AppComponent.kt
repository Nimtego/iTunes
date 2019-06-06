package com.nimtego.plectrum.presentation.di

import com.nimtego.plectrum.presentation.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NavigationModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

//    fun inject(fragment: SampleFragment)
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