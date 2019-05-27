package com.nimtego.plectrum.data.repository.datasource;

import com.nimtego.plectrum.data.model.rss_itunes.Feed;
import com.nimtego.plectrum.data.rest.pojo.AlbumsRepository;
import com.nimtego.plectrum.data.rest.pojo.ArtistsRepository;
import com.nimtego.plectrum.data.rest.pojo.SongsRepository;
import com.nimtego.plectrum.data.rest.pojo.wiki.WikiSearchResult;

import io.reactivex.Observable;

public interface DataStore {

    Observable<WikiSearchResult> wikiSearch(String response);

    Observable<SongsRepository> songs(String response);

    Observable<SongsRepository> songsByIdAlbum(int id);

    Observable<ArtistsRepository> artists(String response);

    Observable<AlbumsRepository> albums(String response);

    Observable<SongsRepository> songById(int id);

    Observable<ArtistsRepository> artistById(int id);

    Observable<AlbumsRepository> album(String response);

    Observable<Feed> recent();

    Observable<Feed> topSong();

    Observable<Feed> topAlbum();

    Observable<Feed> hot();

    Observable<Feed> newMusick();
}
