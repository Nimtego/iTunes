package com.nimtego.itunes.data.cache;

import com.nimtego.itunes.data.entity.Album;

import io.reactivex.Observable;

public interface Cache {

    Observable<Album> get(final String name);

    void put(Album album);

    boolean isCached(final int userId);

    boolean isExpired();

    void evictAll();
}
