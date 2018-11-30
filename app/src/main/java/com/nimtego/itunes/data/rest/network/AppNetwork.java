package com.nimtego.itunes.data.rest.network;

import com.nimtego.itunes.data.rest.network.itunes.ITunesApi;
import com.nimtego.itunes.data.rest.network.wiki.RestCountries;
import com.nimtego.itunes.data.rest.network.wiki.WikiApi;

public interface AppNetwork {
    ITunesApi getITunesClient();
    WikiApi getWikiClient(RestCountries restCountries);
    WikiApi getWikiClient();
}
