package com.nimtego.plectrum.data.repository.repository;

import com.nimtego.plectrum.App;
import com.nimtego.plectrum.data.cache.DashBoardEntityCache;
import com.nimtego.plectrum.data.cache.FileManager;
import com.nimtego.plectrum.data.cache.Serializer;
import com.nimtego.plectrum.data.entity.DashBoardSongsModel;
import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper;
import com.nimtego.plectrum.data.executor.BaseExecutor;
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse;
import com.nimtego.plectrum.data.repository.datasource.DataStore;
import com.nimtego.plectrum.data.repository.datasource.DataStoreFactory;
import com.nimtego.plectrum.data.model.itunes.AlbumResult;
import com.nimtego.plectrum.data.model.itunes.ArtistsRepository;
import com.nimtego.plectrum.domain.repository.Repository;
import com.nimtego.plectrum.presentation.old.information_view.album.model.AlbumDetailsModel;
import com.nimtego.plectrum.presentation.old.information_view.artist.model.ArtistDetailsModelK;
import com.nimtego.plectrum.presentation.old.information_view.song.model.SongDetailsModel;
import com.nimtego.plectrum.presentation.old.main.model.AlbumModel;
import com.nimtego.plectrum.presentation.old.main.model.ArtistModel;
import com.nimtego.plectrum.presentation.old.main.model.SongModel;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AppRepository implements Repository {

    private DataStoreFactory dataStoreFactory;
    private EntityDataMapper mapper;


    public AppRepository(DataStoreFactory dataStoreFactory, EntityDataMapper mapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.mapper = mapper;
    }

    @Inject
    public AppRepository() {
        this(new DataStoreFactory<PopularResponse>(App.Companion.getINSTANCE().getApplicationContext(),
                        new DashBoardEntityCache<>(
                                App.Companion.getINSTANCE(),
                                new Serializer(),
                                new FileManager(),
                                new BaseExecutor())
                ),
                new EntityDataMapper());
    }

    @NotNull
    @Override
    public Observable<List<SongModel>> songs(@NotNull String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.songs(request).map(this.mapper::transformSongs);
    }

    @NotNull
    @Override
    public Observable<List<ArtistModel>> artists(@NotNull String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.artists(request)
                .map(ArtistsRepository::getResults)
                .flatMap(list -> Observable.fromIterable(list).take(3)
                        .flatMap(artistResult -> changeLink(artistResult.getArtistLinkUrl())
                                .map(url -> {
                                    artistResult.setArtistLinkUrl(url);
                                    return artistResult;
                                }))
                        .toList()
                        .toObservable()
                        .map(mapper::transformArtists));
    }

    private Observable<String> changeLink(String oldUrl) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String url = "EMPTY";
                Elements element = Jsoup.connect(oldUrl)
                        .get()
                        .getElementsByClass("we-artwork ember-view we-artist-header__background we-artwork--round we-artwork--no-border");
                if (!element.isEmpty()) {
                    url = element.select("img")
                            .get(0)
                            .attr("src");
                }
                return url;
            }

        });
    }

    @NotNull
    @Override
    public Observable<List<AlbumModel>> albums(@NotNull String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.albums(request).map(this.mapper::transformAlbums);
    }


    @NotNull
    @Override
    public Observable<SongDetailsModel> songDeteil(@NotNull String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.songById(Integer.valueOf(request)).map(result ->
                this.mapper.transformSongDetail(result.getResults().get(0)));
    }

    @NotNull
    @Override
    public Observable<ArtistModel> artist(@NotNull String request) {
        return null;
    }

    @NotNull
    @Override
    public Observable<AlbumDetailsModel> albumDetail(@NotNull String request) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return dataStore.album(request)
                .flatMap(album -> {
                    AlbumResult albumResult = album.getFirst();
                    return Observable.zip(dataStore
                                    .songsByIdAlbum(albumResult.getCollectionId()),
                            dataStore.wikiSearch(albumResult.getArtistName()),
                            (song, wiki) -> {
                                //todo imut
//                                albumDetail.setSongs(mapper.transformSongs(song));
//                                albumDetail.setWikiInformation(wiki.isEmpty() ?
//                                        "No information in wiki"
//                                        : mapper.wikiInformationArtist(wiki));
                                return mapper.transformAlbumDetail(albumResult);
                            });
                });
    }

    @NotNull
    @Override
    public Observable<ArtistDetailsModelK> artistDetail(@NotNull String id) {
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        return Observable.zip(dataStore.artistById(Integer.valueOf(id)),
                dataStore.album(id), (artist, albums) -> {
                    //todo imut
//                    artistDetails.setAlbums(mapper.transformAlbums(albums));
                    return mapper.transformArtistDetail(artist.getResults().get(0));
                }).flatMap(result -> changeLink(result.getArtistArtwork())
                .map(url -> {
                    //todo  imut
//                            result.setArtistArtwork(url);
                    return result;
                }));
    }

    @NotNull
    @Override
    public Observable<DashBoardSongsModel> dashBoardModel() {
        //todo
        final DataStore dataStore = this.dataStoreFactory.createCloudDataStore();
        Observable<PopularResponse> hotOb = dataStore.hot();
        Observable<PopularResponse> newMusickOb = dataStore.newMusic();
        Observable<PopularResponse> topAlbumOb = dataStore.topAlbum();
        Observable<PopularResponse> topSongOb = dataStore.topSong(0);
        return Observable.zip(topAlbumOb,
                topSongOb,
                hotOb,
                newMusickOb,
                (topAlbum, topSong, hotSong, newMusic) ->
                        mapper.dashBoardModel(topSong.getFeed(),
                                topAlbum.getFeed(),
                                newMusic.getFeed(),
                                hotSong.getFeed()
                        )
        );
    }
}

