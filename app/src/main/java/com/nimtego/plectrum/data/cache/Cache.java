package com.nimtego.plectrum.data.cache;

import com.nimtego.plectrum.data.entity.Album;

import io.reactivex.Observable;

public interface Cache {

    Observable<Album> get(final String name);

    void put(Album album);

    boolean isCached(final int userId);

    boolean isExpired();

    void evictAll();
}
