package com.nimtego.plectrum.presentation.di.modules.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.nimtego.plectrum.data.network.itunes.ITunesApi
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
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
    @Named(NetworkQualifiers.RSS_ITUNES_API)
    internal fun provideRssItunesApi(
            @Named(NetworkQualifiers.RSS_ITUNES_API)retrofit: Retrofit
    ): RssItunesApi {
        return retrofit.create(RssItunesApi::class.java)
    }

    @Provides
    @Singleton
    @Named(NetworkQualifiers.ITUNES_API)
    internal fun provideItunesApi(
            @Named(NetworkQualifiers.ITUNES_API)retrofit: Retrofit
    ): ITunesApi {
        return retrofit.create(ITunesApi::class.java)
    }

    @Provides
    @Singleton
    @Named(NetworkQualifiers.ITUNES_API)
    internal fun provideItunesRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_ITUNES)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    @Singleton
    @Named(NetworkQualifiers.WIKI_API)
    internal fun wikiApi(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_WIKI)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    @Singleton
    @Named(NetworkQualifiers.RSS_ITUNES_API)
    internal fun rssITunesApi(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_RSS_ITUNES)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    internal fun gson() = GsonBuilder().create()

    @Provides
    internal fun gsonConverterFactory(gson: Gson) = GsonConverterFactory.create(gson)

    companion object {

        private const val BASE_URL_ITUNES = "https://itunes.apple.com"
        private const val BASE_URL_WIKI = "https://COUNTRY.wikipedia.org/w/"
        private const val BASE_URL_RSS_ITUNES = "https://rss.itunes.apple.com/api/v1/us/"
    }
}
