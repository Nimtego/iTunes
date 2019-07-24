package com.nimtego.plectrum.presentation.di.modules.presentation

import com.nimtego.plectrum.presentation.manger.MainChoiceItemStorage
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class StorageModule {

    private val itemStorage: MainChoiceItemStorage = MainChoiceItemStorage()


    @Provides
    @Singleton
    @Named(StorageQualifiers.MAIN_ITEM_STORAGE_MANAGER)
    internal fun provideStorageManager(): MainChoiceItemStorage {
        return itemStorage
    }
}