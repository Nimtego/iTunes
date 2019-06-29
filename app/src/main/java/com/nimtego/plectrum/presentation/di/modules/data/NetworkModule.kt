package com.nimtego.plectrum.presentation.di.modules.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("api_itunes")
    fun itunesApi(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_ITUNES)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    @Singleton
    @Named("api_wiki")
    fun wikiApi(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_WIKI)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    @Singleton
    @Named("api_rss_itunes")
    fun rssITunesApi(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_RSS_ITUNES)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    fun gson() = GsonBuilder().create()

    @Provides
    fun gsonConverterFactory(gson: Gson) = GsonConverterFactory.create(gson)

    companion object {

        private const val BASE_URL_ITUNES = "https://itunes.apple.com"
        private const val BASE_URL_WIKI = "https://COUNTRY.wikipedia.org/w/"
        private const val BASE_URL_RSS_ITUNES = "https://rss.itunes.apple.com/api/v1/us/itunes-music/"
    }
}
