package com.nimtego.plectrum.presentation.di.modules.data

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.data.repository.datasource.dash_board.CloudSongRecent
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module(includes = [RepositoryModule::class, NetworkModule::class])
class DataStoreModule {

    @Provides
    fun songDataStore(@Named("api_rss_itunes")retrofit: Retrofit, cache: Cache<PopularResponse>) : CloudSongRecent<PopularResponse> {
        val api = retrofit.create(RssItunesApi::class.java)
        return CloudSongRecent(api, cache)
    }
}