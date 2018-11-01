package com.nimtego.itunes.data.repository.datasource;

import com.nimtego.itunes.data.cache.Cache;
import com.nimtego.itunes.data.rest.network.ITunesApi;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongResult;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;

import java.util.List;

import io.reactivex.Observable;

public class CloudDataStore implements DataStore{


    public CloudDataStore(ITunesApi restApi, Cache cache) {
    }

    @Override
    public Observable<SongsRepository> songs() {
        return null;
    }

    @Override
    public Observable<ArtistsRepository> artists() {
        return null;
    }

    @Override
    public Observable<List<AlbumResult>> albums(String request) {
        return null;
    }

    @Override
    public Observable<SongResult> song() {
        return null;
    }

    @Override
    public Observable<ArtistResult> artist() {
        return null;
    }

    @Override
    public Observable<AlbumResult> album() {
        return null;
    }
}
