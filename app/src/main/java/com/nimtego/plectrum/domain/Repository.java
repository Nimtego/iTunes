package com.nimtego.plectrum.domain;

import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModelK;
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK;
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModelK;
import com.nimtego.plectrum.presentation.main.model.AlbumModel;
import com.nimtego.plectrum.presentation.main.model.ArtistModel;
import com.nimtego.plectrum.presentation.main.model.SongModel;

import java.util.List;

import io.reactivex.Observable;

public interface Repository {
    Observable<List<SongModel>> songs(String request);

    Observable<List<ArtistModel>> artists(String request);

    Observable<List<AlbumModel>> albums(String request);

    Observable<SongDetailsModelK> songDeteil(String name);

    Observable<ArtistModel> artist(String name);

    Observable<AlbumDetailsModelK> albumDeteil(String name);

    Observable<ArtistDetailsModelK> artistDetail(String name);

}
