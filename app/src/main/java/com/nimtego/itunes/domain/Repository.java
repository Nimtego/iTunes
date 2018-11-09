package com.nimtego.itunes.domain;

import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.ArtistModel;
import com.nimtego.itunes.presentation.main.model.SongModel;

import java.util.List;

import io.reactivex.Observable;

public interface Repository {
    Observable<List<SongModel>> songs(String request);

    Observable<List<ArtistModel>> artists(String request);

    Observable<List<AlbumModel>> albums(String request);

    Observable<SongModel> song(int id);

    Observable<ArtistModel> artist(int id);

    Observable<AlbumModel> album(int id);
}
