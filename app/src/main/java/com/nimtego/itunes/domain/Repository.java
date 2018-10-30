package com.nimtego.itunes.domain;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;

import java.util.List;

import io.reactivex.Observable;

public interface Repository {
    Observable<List<Song>> songs();
    Observable<List<Artist>> artists();
    Observable<List<Album>> albums();
    Observable<Song> song();
    Observable<Artist> artist();
    Observable<Album> album();
}
