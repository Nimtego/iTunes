package com.nimtego.itunes.data.repository.datasource;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongResult;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;

import java.util.List;

import io.reactivex.Observable;

public interface DataStore {
    Observable<SongsRepository> songs(String request);
    Observable<ArtistsRepository> artists(String request);
    Observable<AlbumsRepository> albums(String request);
    Observable<SongResult> song();
    Observable<ArtistResult> artist();
    Observable<AlbumResult> album();
}
