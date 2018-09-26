package com.nimtego.itunes.model;

import com.nimtego.itunes.service.EntityRepository;
import com.nimtego.itunes.service.ResultEntity;

import java.util.Collections;
import java.util.List;

public interface ModelManager<T extends ResultEntity> {
    List<T> getListAlbum();
    T getAlbumByName(String name);
    void setAlbumCollection(List<T> list, String valueSearch);
    boolean searchCheck();
}
