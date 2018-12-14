package com.nimtego.itunes.presentation.di.components;

import com.nimtego.itunes.presentation.di.modules.presentation.PresenterModule;
import com.nimtego.itunes.presentation.main.albums.AlbumContract;
import com.nimtego.itunes.presentation.main.artists.ArtistContract;
import com.nimtego.itunes.presentation.main.songs.SongContract;

import dagger.Component;

@Component(modules = PresenterModule.class)
public interface PresenterComponent {
    ArtistContract.Presenter mainArtistPresenter();
    AlbumContract.Presenter mainAlbumPresenter();
    SongContract.Presenter mainSongPresenter();
}
