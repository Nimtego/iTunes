package com.nimtego.plectrum.data.repository.datasource

import android.content.Context

import com.nimtego.plectrum.data.cache.Cache
import com.nimtego.plectrum.data.network.NetworkConnection

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.annotations.NonNull

@Singleton
class DataStoreFactory<E>
@Inject
constructor(private val context: Context,
            private val cache: Cache<E>) {

    fun create(userId: Int): DataStore {
        val userDataStore: DataStore

        if (!this.cache.isExpired() && this.cache.isCached(userId.toString())) {
            userDataStore = DiskDataStore(this.cache)
        } else {
            userDataStore = createCloudDataStore()
        }

        return userDataStore
    }

    fun createCloudDataStore(): DataStore {

        return CloudDataStore(NetworkConnection.getInstance(), this.cache)
    }
}
