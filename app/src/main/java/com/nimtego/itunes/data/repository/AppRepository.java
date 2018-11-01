package com.nimtego.itunes.data.repository;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.data.entity.mapper.EntityDataMapper;
import com.nimtego.itunes.data.repository.datasource.DataStore;
import com.nimtego.itunes.data.repository.datasource.DataStoreFactory;
import com.nimtego.itunes.domain.Repository;

import java.util.List;

import io.reactivex.Observable;

public class AppRepository implements Repository {

    private DataStoreFactory dataStoreFactory;
    private EntityDataMapper mapper;

    @Override
    public Observable<List<Song>> songs() {
        return null;
    }

    @Override
    public Observable<List<Artist>> artists() {
        return null;
    }

    @Override
    public Observable<List<Album>> albums(String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.albums(request).map(this.mapper::transformAlbums);
    }


    @Override
    public Observable<Song> song() {
        return null;
    }

    @Override
    public Observable<Artist> artist() {
        return null;
    }

    @Override
    public Observable<Album> album() {
        return null;
    }
}
