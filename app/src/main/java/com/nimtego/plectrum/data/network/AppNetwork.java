package com.nimtego.plectrum.data.network;

import com.nimtego.plectrum.data.network.itunes.ITunesApi;
import com.nimtego.plectrum.data.network.rss_itunes.RssItunesApi;
import com.nimtego.plectrum.data.network.wiki.RestCountries;
import com.nimtego.plectrum.data.network.wiki.WikiApi;

public interface AppNetwork {
    ITunesApi getITunesClient();
    WikiApi getWikiClient(RestCountries restCountries);
    WikiApi getWikiClient();
    RssItunesApi getRssItunesAPi();
}
