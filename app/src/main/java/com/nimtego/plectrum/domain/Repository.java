package com.nimtego.plectrum.domain;

import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModel;
import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModelK;
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModel;
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK;
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModel;
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModelK;
import com.nimtego.plectrum.presentation.main.model.AlbumModel;
import com.nimtego.plectrum.presentation.main.model.AlbumModelK;
import com.nimtego.plectrum.presentation.main.model.ArtistModel;
import com.nimtego.plectrum.presentation.main.model.ArtistModelK;
import com.nimtego.plectrum.presentation.main.model.SongModel;
import com.nimtego.plectrum.presentation.main.model.SongModelK;

import java.util.List;

import io.reactivex.Observable;

public interface Repository {
    Observable<List<SongModelK>> songs(String request);

    Observable<List<ArtistModelK>> artists(String request);

    Observable<List<AlbumModelK>> albums(String request);

    Observable<SongDetailsModelK> songDeteil(String name);

    Observable<ArtistModelK> artist(String name);

    Observable<AlbumDetailsModelK> albumDeteil(String name);

    Observable<ArtistDetailsModelK> artistDetail(String name);

}
