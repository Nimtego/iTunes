package com.nimtego.plectrum.presentation.di.modules.presentation;

import com.nimtego.plectrum.presentation.main.MainContract;
import com.nimtego.plectrum.presentation.main.MainPresenter;
import com.nimtego.plectrum.presentation.main.albums.AlbumContract;
import com.nimtego.plectrum.presentation.main.albums.AlbumPresenter;
import com.nimtego.plectrum.presentation.main.artists.ArtistContract;
import com.nimtego.plectrum.presentation.main.artists.ArtistPresenter;
import com.nimtego.plectrum.presentation.main.songs.SongContract;
import com.nimtego.plectrum.presentation.main.songs.SongPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    public ArtistContract.Presenter mainArtistPresenter() {
        return new ArtistPresenter();
    }

    @Provides
    public AlbumContract.Presenter mainAlbumPresenter() {
        return new AlbumPresenter();
    }

    @Provides
    public SongContract.Presenter mainSongPresenter() {
        return new SongPresenter();
    }

    @Provides
    public MainContract.Presenter mainPresenter() {
        return new MainPresenter();
    }
}
