package com.nimtego.plectrum.presentation.di.modules.presentation

import android.content.Context
import com.nimtego.plectrum.presentation.di.modules.ContextModule
import com.nimtego.plectrum.presentation.manger.*
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class StorageModule {

    private val userChoiceItemStorage: MainChoiceItemStorage = MainChoiceItemStorage()
    private val userSearchItemStorage: UserSearchStorage = UserSearchStorage()


    @Provides
    @Singleton
    @Named(StorageQualifiers.MAIN_ITEM_STORAGE_MANAGER)
    internal fun provideStorageManager(): MainChoiceItemStorage {
        return userChoiceItemStorage
    }

    @Provides
    @Singleton
    internal fun provideUserSearchItemStorage(): UserSearchItemStorage {
        return userSearchItemStorage
    }

    @Provides
    @Singleton
    internal fun provideResourceManager(context: Context): ResourceManager {
        return AppResourceManager(context)
    }

    @Provides
    @Singleton
    internal fun provideTabProvider(): TabsProvider {
        return SearchTabsProvider()
    }
}