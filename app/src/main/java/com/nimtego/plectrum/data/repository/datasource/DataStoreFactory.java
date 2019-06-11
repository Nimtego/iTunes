package com.nimtego.plectrum.data.repository.datasource;

import android.content.Context;

import com.nimtego.plectrum.data.cache.Cache;
import com.nimtego.plectrum.data.cache.CacheK;
import com.nimtego.plectrum.data.rest.network.NetworkConnection;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;

@Singleton
public class DataStoreFactory<E> {

    private final Context context;
    private final CacheK<E> cache;

    @Inject
    public DataStoreFactory(@NonNull Context context, @NonNull CacheK<E> cache) {
        this.context = context.getApplicationContext();
        this.cache = cache;
    }

    public DataStore create(int userId) {
        DataStore userDataStore;

        if (!this.cache.isExpired() && this.cache.isCached(String.valueOf(userId))) {
            userDataStore = new DiskDataStore(this.cache);
        } else {
            userDataStore = createCloudDataStore();
        }

        return userDataStore;
    }

    public DataStore createCloudDataStore() {

        return new CloudDataStore(NetworkConnection.getInstance(), this.cache);
    }
}
