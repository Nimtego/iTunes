package com.nimtego.itunes.model;

import com.nimtego.itunes.service.pojo.AlbumResult;


import java.util.List;

public interface ModelManager<T extends AlbumResult> {
    List<T> getListAlbum();
    T getAlbumByName(String name);
    void setAlbumCollection(List<T> list, String valueSearch);
    boolean searchCheck();
}
