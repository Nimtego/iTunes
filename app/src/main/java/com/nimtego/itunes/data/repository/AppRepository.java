package com.nimtego.itunes.data.repository;

import com.nimtego.itunes.App;
import com.nimtego.itunes.data.cache.AlbumCache;
import com.nimtego.itunes.data.cache.FileManager;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.mapper.EntityDataMapper;
import com.nimtego.itunes.data.repository.datasource.DataStore;
import com.nimtego.itunes.data.repository.datasource.DataStoreFactory;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.domain.Repository;
import com.nimtego.itunes.presentation.information_view.album.model.AlbumDetailsModel;
import com.nimtego.itunes.presentation.information_view.artist.model.ArtistDetailsModel;
import com.nimtego.itunes.presentation.information_view.song.model.SongDetailsModel;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.ArtistModel;
import com.nimtego.itunes.presentation.main.model.SongModel;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

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
        return null;
    }
/*        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        *//*return dataStore.artists(request).map(this.mapper::transformArtists);*//*
        return dataStore.artists(request).flatMap(result -> {
            List<ArtistResult> artistResults = result.getResults();
            return Observable.zip(dataStore
                            .songsByIdAlbum(request),
                    changeLink(artistResults.),
                    (song, wiki) -> {
                        AlbumDetailsModel albumDetail =
                                mapper.transformAlbumDetail(albumResult);
                        albumDetail.setSongs(mapper.transformSongs(song));
                        albumDetail.setWikiInformation(wiki.isEmpty() ?
                                "No information in wiki"
                                : mapper.wikiInformationArtist(wiki));
                        return albumDetail;
                    });
        });
    }*/

    /*       return dataStore.artists(request).map(this.mapper::transformArtists)
                   .flatMap(result -> {
               return Observable.fromCallable(new Callable<List<ArtistModel>>() {
                   @Override
                   public List<ArtistModel> call() {
                       return result.stream()
                               .map(re -> {
                                   String url = changeLink2(re.getArtistViewUrl());
                                   re.setArtistViewUrl(url);
                                   return re;
                               }).collect(Collectors.toList());
                   }
               });
           });
       }*/

    private Observable<String> changeLink(String oldUrl) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return changeLink2(oldUrl);
            }
        });
/*        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(changeLink2(oldUrl));
            }
        });*/
    }

    private String changeLink2(String oldUrl) throws IOException {
        return Jsoup.connect(oldUrl)
                .get()
                .getElementsByClass("we-artwork ember-view we-artist-header__background we-artwork--round we-artwork--no-border")
                .select("img")
                .get(0)
                .attr("src");
    }

    @Override
    public Observable<List<AlbumModel>> albums(String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.albums(request).map(this.mapper::transformAlbums);
    }


    @Override
    public Observable<SongDetailsModel> songDeteil(String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.songById(Integer.valueOf(request)).map(result ->
                this.mapper.transformSongDetail(result.getResults().get(0)));
    }

    @Override
    public Observable<ArtistModel> artist(String request) {
        return null;
    }

    @Override
    public Observable<AlbumDetailsModel> albumDeteil(String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.album(request)
                .flatMap(album -> {
                    AlbumResult albumResult = album.getFirst();
                    return Observable.zip(dataStore
                                    .songsByIdAlbum(albumResult.getCollectionId()),
                            dataStore.wikiSearch(albumResult.getArtistName()),
                            (song, wiki) -> {
                                AlbumDetailsModel albumDetail =
                                        mapper.transformAlbumDetail(albumResult);
                                albumDetail.setSongs(mapper.transformSongs(song));
                                albumDetail.setWikiInformation(wiki.isEmpty() ?
                                        "No information in wiki"
                                        : mapper.wikiInformationArtist(wiki));
                                return albumDetail;
                            });
                });
    }

    @Override
    public Observable<ArtistDetailsModel> artistDetail(String id) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return Observable.zip(dataStore.artistById(Integer.valueOf(id)),
                dataStore.album(id), (artist, albums) -> {
                    ArtistDetailsModel artistDetails = mapper.transformArtistDetail(artist.getResults().get(0));
                    artistDetails.setAlbums(mapper.transformAlbums(albums));
                    return artistDetails;
                });
    }
}

