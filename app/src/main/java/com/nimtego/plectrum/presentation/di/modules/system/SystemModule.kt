package com.nimtego.plectrum.presentation.di.modules.system

import com.nimtego.plectrum.presentation.ui.massage.SystemMessageNotifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SystemModule {

    @Provides
    @Singleton
    fun systemMessage() = SystemMessageNotifier()
}