package com.nimtego.itunes.domain;

import com.nimtego.itunes.presentation.information_view.album.model.AlbumDetailsModel;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.ArtistModel;
import com.nimtego.itunes.presentation.main.model.SongModel;

import java.util.List;

import io.reactivex.Observable;

public interface Repository {
    Observable<List<SongModel>> songs(String request);

    Observable<List<ArtistModel>> artists(String request);

    Observable<List<AlbumModel>> albums(String request);

    Observable<SongModel> song(String name);

    Observable<ArtistModel> artist(String name);

    Observable<AlbumDetailsModel> album(String name);

    //Observable<AlbumDetailsModel> album(String name);
}
