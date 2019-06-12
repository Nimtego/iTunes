package com.nimtego.plectrum.domain.repository;

import com.nimtego.plectrum.data.entity.DashBoardModel;
import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModel;
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK;
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModel;
import com.nimtego.plectrum.presentation.main.model.AlbumModel;
import com.nimtego.plectrum.presentation.main.model.ArtistModel;
import com.nimtego.plectrum.presentation.main.model.SongModel;

import java.util.List;

import io.reactivex.Observable;

public interface Repository {
    Observable<List<SongModel>> songs(String request);

    Observable<List<ArtistModel>> artists(String request);

    Observable<List<AlbumModel>> albums(String request);

    Observable<SongDetailsModel> songDeteil(String name);

    Observable<ArtistModel> artist(String name);

    Observable<AlbumDetailsModel> albumDeteil(String name);

    Observable<ArtistDetailsModelK> artistDetail(String name);

    Observable<DashBoardModel> dashBoardModel();
}
