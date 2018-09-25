package com.nimtego.itunes.model;

import com.nimtego.itunes.service.EntityRepository;
import com.nimtego.itunes.service.ResultEntity;

import java.util.Collections;
import java.util.List;

public interface ModelManager<T extends ResultEntity> {
    public List<T> getListAlbum();
    public T getAlbumByName(String name);
}
