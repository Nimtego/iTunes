package com.nimtego.plectrum.presentation.di.modules.presentation;

import com.nimtego.plectrum.domain.interactor.DashBoardInteractor;
import com.nimtego.plectrum.presentation.di.NavigationModule;
import com.nimtego.plectrum.presentation.di.modules.data.RepositoryModule;
import com.nimtego.plectrum.presentation.main.MainPresenter;
import com.nimtego.plectrum.presentation.main.albums.AlbumPresenterImpl;
import com.nimtego.plectrum.presentation.main.albums.AlbumPresenter;
import com.nimtego.plectrum.presentation.main.artists.ArtistPresenterImpl;
import com.nimtego.plectrum.presentation.main.artists.ArtistPresenter;
import com.nimtego.plectrum.presentation.main.songs.SongPresenterImpl;
import com.nimtego.plectrum.presentation.main.songs.SongPresenter;
import com.nimtego.plectrum.presentation.mvp.presenters.TabContentPresenter;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;

@Module(includes = {RepositoryModule.class,
                    NavigationModule.class})
public class PresenterModule {

    @Provides
    public ArtistPresenter mainArtistPresenter() {
        return new ArtistPresenterImpl();
    }

    @Provides
    public AlbumPresenter mainAlbumPresenter() {
        return new AlbumPresenterImpl();
    }

    @Provides
    public SongPresenter mainSongPresenter() {
        return new SongPresenterImpl();
    }

    @Provides
    public MainPresenter mainPresenter() {
        return new MainPresenter();
    }

    @Provides
    public TabContentPresenter tabContentPresenter(Router router,
                                                   DashBoardInteractor dashBoardInteractor) {
        return new TabContentPresenter(router, 1, dashBoardInteractor);
    }
}
