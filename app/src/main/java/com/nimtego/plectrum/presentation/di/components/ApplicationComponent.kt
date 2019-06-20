package com.nimtego.plectrum.presentation.di.components

import com.nimtego.plectrum.presentation.di.modules.ContextModule
import com.nimtego.plectrum.presentation.di.modules.domain.InteractorModule
import com.nimtego.plectrum.presentation.di.modules.presentation.PresenterModule
import com.nimtego.plectrum.presentation.ui.activity.MainActivity
import com.nimtego.plectrum.presentation.ui.fragment.DashboardFragment
import com.nimtego.plectrum.presentation.ui.fragment.MoreSectionFragment
import com.nimtego.plectrum.presentation.ui.fragment.TabContentFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: DashboardFragment)

    fun inject(fragment: TabContentFragment)

    fun inject(fragment: MoreSectionFragment)

}