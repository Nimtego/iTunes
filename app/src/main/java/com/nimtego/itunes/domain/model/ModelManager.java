package com.nimtego.itunes.domain.model;


import com.nimtego.itunes.presentation.mvp_contracts.AlbumsCollectionContract;

public interface ModelManager {
    void getAlbums(AlbumsCollectionContract.OnFinishedListener listener, String request);
    void getArtists(AlbumsCollectionContract.OnFinishedListener listener, String request);
    void getSongs(AlbumsCollectionContract.OnFinishedListener listener, String request);
}
