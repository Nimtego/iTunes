package com.nimtego.plectrum.data.cache

import io.reactivex.Observable

interface Cache<K, V> {

    fun get(key: K): Observable<V>

    fun put(key: K, value: V)

    fun isCached(key: K): Boolean

    fun isExpired(): Boolean

    fun evictAll()

}