package com.nimtego.plectrum.presentation.di.components;

import com.nimtego.plectrum.presentation.di.modules.presentation.PresenterModule;
import com.nimtego.plectrum.presentation.main.MainPresenter;
import com.nimtego.plectrum.presentation.main.albums.AlbumPresenter;
import com.nimtego.plectrum.presentation.main.artists.ArtistPresenter;
import com.nimtego.plectrum.presentation.main.songs.SongPresenter;

import dagger.Component;

@Component(modules = PresenterModule.class)
public interface PresenterComponent {
    ArtistPresenter mainArtistPresenter();
    AlbumPresenter mainAlbumPresenter();
    SongPresenter mainSongPresenter();
    MainPresenter mainPresenter();

}
