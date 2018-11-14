package com.nimtego.itunes.data.repository.datasource;

import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongResult;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;
import com.nimtego.itunes.presentation.information_view.model.AlbumDetailsModel;

import io.reactivex.Observable;

public interface DataStore {
    Observable<SongsRepository> songs(String response);

    Observable<ArtistsRepository> artists(String response);

    Observable<AlbumsRepository> albums(String response);

    Observable<SongResult> song();

    Observable<ArtistResult> artist();

    Observable<AlbumResult> album(String response);
}
