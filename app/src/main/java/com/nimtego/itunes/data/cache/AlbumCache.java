package com.nimtego.itunes.data.cache;

import android.content.Context;

import com.nimtego.itunes.data.entity.Album;

import java.io.File;

import io.reactivex.Observable;

public class AlbumCache implements Cache {
    private static final String SETTINGS_FILE_NAME = "com.fernandocejas.android10.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final FileManager fileManager;

    public AlbumCache(Context context,
                      FileManager fileManager) {
        if (context == null || fileManager == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.fileManager = fileManager;
    }

    @Override
    public Observable<Album> get(String name) {
        return null;
    }

    @Override
    public void put(Album album) {

    }

    @Override
    public boolean isCached(int userId) {
        return false;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {

    }
}
