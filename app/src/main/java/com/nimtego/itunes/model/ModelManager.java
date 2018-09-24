package com.nimtego.itunes.model;

import com.nimtego.itunes.service.EntityRepository;
import com.nimtego.itunes.service.ResultEntity;

import java.util.List;

public class ModelManager {
    private EntityRepository mResultEntityList;

    public void setAlbumCollection(EntityRepository albumCollection) {
        mResultEntityList = albumCollection;
    }
    public List<ResultEntity> getListAlbum() {
        return mResultEntityList.getResults();
    }
}
