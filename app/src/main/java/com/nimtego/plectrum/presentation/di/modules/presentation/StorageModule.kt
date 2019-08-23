package com.nimtego.plectrum.presentation.di.modules.presentation

import com.nimtego.plectrum.presentation.manger.MainChoiceItemStorage
import com.nimtego.plectrum.presentation.manger.UserSearchItemStorage
import com.nimtego.plectrum.presentation.manger.UserSearchStorage
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
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
}