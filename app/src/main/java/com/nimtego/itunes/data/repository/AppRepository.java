package com.nimtego.itunes.data.repository;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.domain.Repository;

import java.util.List;

import io.reactivex.Observable;

public class AppRepository implements Repository {

    @Override
    public Observable<List<Song>> songs() {
        return null;
    }

    @Override
    public Observable<List<Artist>> artists() {
        return null;
    }

    @Override
    public Observable<List<Album>> albums() {
        return null;
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
