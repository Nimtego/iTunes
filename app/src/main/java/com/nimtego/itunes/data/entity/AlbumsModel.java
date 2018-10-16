package com.nimtego.itunes.data.entity;


import com.nimtego.itunes.domain.model.ModelManager;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.presentation.mvp_contracts.AlbumsCollectionContract;

import java.util.List;

@Deprecated
public class AlbumsModel<T extends AlbumResult> implements ModelManager {
    private List<T> result;
    private String valueSearch = null;


/*
    @Override
    public List<T> getListAlbum() {
        return result;
    }
    @Override
    public T getAlbumByName(String name) {
        for (AlbumResult re : result) {
            if (re.getCollectionName().equals(name)) {
                return (T) re;
            }
        }
        return null;
    }

    @Override
    public void setAlbumCollection(List<T> list, String valueSearch) {
        result = list;
        this.valueSearch = valueSearch;
    }

    @Override
    public boolean searchCheck() {
        return valueSearch == null;
    }
*/

    @Override
    public void getAlbums(AlbumsCollectionContract.OnFinishedListener listener, String request) {

    }

    @Override
    public void getArtists(AlbumsCollectionContract.OnFinishedListener listener, String request) {

    }

    @Override
    public void getSongs(AlbumsCollectionContract.OnFinishedListener listener, String request) {

    }
}
