package com.nimtego.plectrum.data.cache

import com.nimtego.plectrum.data.entity.Album
import io.reactivex.Observable

interface CacheK<E> {

    fun get(id: String): Observable<E>

    fun put(entity: E)

    fun isCached(id: String): Boolean

    fun isExpired(): Boolean

    fun evictAll()

}