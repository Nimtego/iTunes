package com.nimtego.itunes.model;

import com.nimtego.itunes.service.EntityRepository;
import com.nimtego.itunes.service.ResultEntity;

import java.util.List;

public class AlbumsModel<T extends ResultEntity> implements ModelManager<T>{
    private List<T> result;
    private String valueSearch = null;


    @Override
    public List<T> getListAlbum() {
        return result;
    }
    @Override
    public T getAlbumByName(String name) {
        for (ResultEntity re : result) {
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
}
