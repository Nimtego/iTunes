package com.nimtego.plectrum.data.network

import com.nimtego.plectrum.data.network.itunes.ITunesApi
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi
import com.nimtego.plectrum.data.network.wiki.RestCountries
import com.nimtego.plectrum.data.network.wiki.WikiApi

interface AppNetwork {
    val iTunesClient: ITunesApi
    val wikiClient: WikiApi
    val rssItunesAPi: RssItunesApi
    fun getWikiClient(restCountries: RestCountries): WikiApi
}
