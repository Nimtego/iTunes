package com.nimtego.plectrum.data.repository.datasource;

import com.nimtego.plectrum.data.cache.Cache;
import com.nimtego.plectrum.data.model.rss_itunes.Feed;
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse;
import com.nimtego.plectrum.data.rest.pojo.AlbumsRepository;
import com.nimtego.plectrum.data.rest.pojo.ArtistsRepository;
import com.nimtego.plectrum.data.rest.pojo.SongsRepository;
import com.nimtego.plectrum.data.rest.pojo.wiki.WikiSearchResult;

import io.reactivex.Observable;

public class DiskDataStore implements DataStore {

    private final Cache cache;

    public DiskDataStore(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Observable<WikiSearchResult> wikiSearch(String response) {
        return null;
    }

    @Override
    public Observable<SongsRepository> songs(String request) {
        return null;
    }

    @Override
    public Observable<SongsRepository> songsByIdAlbum(int id) {
        return null;
    }

    @Override
    public Observable<ArtistsRepository> artists(String request) {
        return null;
    }


    @Override
    public Observable<AlbumsRepository> albums(String request) {
        return null;
    }

    @Override
    public Observable<SongsRepository> songById(int id) {
        return null;
    }

    @Override
    public Observable<ArtistsRepository> artistById(int id) {
        return null;
    }


    @Override
    public Observable<AlbumsRepository> album(String response) {
        return null;
    }

    @Override
    public Observable<PopularResponse> recent() {
        return null;
    }

    @Override
    public Observable<PopularResponse> topSong() {
        return null;
    }

    @Override
    public Observable<PopularResponse> topAlbum() {
        return null;
    }

    @Override
    public Observable<PopularResponse> hot() {
        return null;
    }

    @Override
    public Observable<PopularResponse> newMusick() {
        return null;
    }
}
