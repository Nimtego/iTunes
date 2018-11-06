package com.nimtego.itunes.domain;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.presentation.main.model.MainDataModel;

import java.util.List;

import io.reactivex.Observable;

public interface Repository {
    Observable<List<Song>> songs(String request);
    Observable<List<Artist>> artists();
    Observable<List<Album>> albums(String request);
    Observable<Song> song();
    Observable<Artist> artist();
    Observable<Album> album();
}
