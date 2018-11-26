package com.nimtego.itunes.data.repository;

import com.nimtego.itunes.App;
import com.nimtego.itunes.data.cache.AlbumCache;
import com.nimtego.itunes.data.cache.FileManager;
import com.nimtego.itunes.data.entity.mapper.EntityDataMapper;
import com.nimtego.itunes.data.repository.datasource.DataStore;
import com.nimtego.itunes.data.repository.datasource.DataStoreFactory;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;
import com.nimtego.itunes.data.rest.pojo.wiki.WikiSearchResult;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.information_view.model.AlbumDetailsModel;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.ArtistModel;
import com.nimtego.itunes.presentation.main.model.SongModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class AppRepository implements Repository {

    private DataStoreFactory dataStoreFactory;
    private EntityDataMapper mapper;


    public AppRepository(DataStoreFactory dataStoreFactory, EntityDataMapper mapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.mapper = mapper;
    }

    public AppRepository() {
        this(new DataStoreFactory(App.getAppContext(),
                new AlbumCache(App.getAppContext(),
                        new FileManager())), new EntityDataMapper());
    }

    @Override
    public Observable<List<SongModel>> songs(String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.songs(request).map(this.mapper::transformSongs);
    }

    @Override
    public Observable<List<ArtistModel>> artists(String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.artists(request).map(this.mapper::transformArtists);
    }

    @Override
    public Observable<List<AlbumModel>> albums(String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.albums(request).map(this.mapper::transformAlbums);
    }


    @Override
    public Observable<SongModel> song(String request) {
        return null;
    }

    @Override
    public Observable<ArtistModel> artist(String request) {
        return null;
    }

    private Observable<List<SongModel>> albumSongsList(int id) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.songsByIdAlbum(id).map(this.mapper::transformSongs);
    }

    @Override
    public Observable<AlbumDetailsModel> album(String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        Observable<AlbumsRepository> albumDetails = dataStore.album(request);
        Observable<WikiSearchResult> wikiSearch = dataStore.wikiSearch(request);
        return Observable.combineLatest(albumDetails, wikiSearch,
                (album, wiki) -> {
                    AlbumDetailsModel albumDetail = AlbumDetailsModel.builder()
                            .albumName(album.getResults().get(0).getCollectionName())
                            .albumArtistName(album.getResults().get(0).getArtistName())
                            .albumArtwork(album.getResults().get(0).getArtworkUrl100())
                            .collectionPrice(album.getResults().get(0).getCollectionPrice())
                            .releaseDate(album.getResults().get(0).getReleaseDate())
                            .albumId(album.getResults().get(0).getCollectionId())
                            .wikiInformation(wiki.getQuery().getPages().getTitle())
                            .build();
                    albumSongsList(albumDetail.getAlbumId())
                            .subscribe(albumDetail::setSongs);
                    return albumDetail;
                });
    }
}

/*mapper.transformAlbumDetail(album.getResults().get(0)).setWikiInformation("  ");*/
                       /*     album  ..map(s ->this.mapper.transformAlbumDetail(s.getResults().get(0)))
                            album.setWikiInformation(wiki.getQuery().);*/



/*        Observable<AlbumDetailsModel>> albumObs = this.album(params.request);
        Observable<List<SongModel>> songsObs = repository.songs(params.request);
        Observable<List<ArtistModel>> artistsObs = repository.artists(params.request);
        return Observable.combineLatest(albumsObs, songsObs, artistsObs, (albums, songs, artists) ->
                MainDataModel.builder()
                        .albumModels(albums)
                        .songModels(songs)
                        .artistModels(artists)
                        .build());



        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.album(request)
                .map(s -> this.mapper.transformAlbumDetail(s.getResults().get(0)));*/
// TODO: 14.11.2018
