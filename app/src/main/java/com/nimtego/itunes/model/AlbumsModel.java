package com.nimtego.itunes.model;


import com.nimtego.itunes.service.pojo.AlbumResult;

import java.util.List;

public class AlbumsModel<T extends AlbumResult> implements ModelManager{
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
    public List<Album> getAlbums() {
        return null;
    }

    @Override
    public List<Artist> getArtists() {
        return null;
    }

    @Override
    public List<Song> getSongs() {
        return null;
    }
}
