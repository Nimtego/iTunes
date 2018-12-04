package com.nimtego.itunes.data.repository.datasource;

import com.nimtego.itunes.data.cache.Cache;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongResult;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;
import com.nimtego.itunes.data.rest.pojo.wiki.WikiSearchResult;

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
}
