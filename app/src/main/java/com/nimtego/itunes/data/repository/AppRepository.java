package com.nimtego.itunes.data.repository;

import com.nimtego.itunes.App;
import com.nimtego.itunes.data.cache.AlbumCache;
import com.nimtego.itunes.data.cache.FileManager;
import com.nimtego.itunes.data.entity.mapper.EntityDataMapper;
import com.nimtego.itunes.data.repository.datasource.DataStore;
import com.nimtego.itunes.data.repository.datasource.DataStoreFactory;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.information_view.model.AlbumDetailsModel;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.ArtistModel;
import com.nimtego.itunes.presentation.main.model.SongModel;

import java.util.List;

import io.reactivex.Observable;

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
        return albumDetails.flatMap(r -> Observable.combineLatest(dataStore.songsByIdAlbum(r.getResults().get(0).getCollectionId()),
                dataStore.wikiSearch(r.getResults().get(0).getArtistName()),
                (song, wiki) -> {
                    AlbumDetailsModel albumDetail =
                            mapper.transformAlbumDetail(r.getResults().get(0));
                    albumDetail.setSongs(mapper.transformSongs(song));
                    albumDetail.setWikiInformation(wiki.getQuery().getSearch().get(0).getSnippet());
                    return albumDetail;
                }));
    }
}

