package com.nimtego.itunes.model;

import com.nimtego.itunes.service.EntityRepository;
import com.nimtego.itunes.service.ResultEntity;

import java.util.List;

public class AlbumsModel implements ModelManager{
    private EntityRepository mResultEntityList;


    public void setAlbumCollection(EntityRepository albumCollection) {
        mResultEntityList = albumCollection;
    }
    @Override
    public List<ResultEntity> getListAlbum() {
        return mResultEntityList.getResults();
    }
    @Override
    public ResultEntity getAlbumByName(String name) {
        for (ResultEntity re : getListAlbum()) {
            if (re.getCollectionName().equals(name)) {
                return re;
            }
        }
        return null;
    }
}
