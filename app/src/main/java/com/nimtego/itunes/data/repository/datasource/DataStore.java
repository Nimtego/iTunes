package com.nimtego.itunes.data.repository.datasource;

import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongResult;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;
import com.nimtego.itunes.data.rest.pojo.wiki.WikiSearchResult;

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
}
