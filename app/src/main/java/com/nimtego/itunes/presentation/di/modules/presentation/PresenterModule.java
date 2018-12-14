package com.nimtego.itunes.presentation.di.modules.presentation;

import com.nimtego.itunes.presentation.main.albums.AlbumContract;
import com.nimtego.itunes.presentation.main.albums.AlbumPresenter;
import com.nimtego.itunes.presentation.main.artists.ArtistContract;
import com.nimtego.itunes.presentation.main.artists.ArtistPresenter;
import com.nimtego.itunes.presentation.main.songs.SongContract;
import com.nimtego.itunes.presentation.main.songs.SongPresenter;

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
}
