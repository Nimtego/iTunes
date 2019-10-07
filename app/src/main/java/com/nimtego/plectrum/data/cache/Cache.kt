package com.nimtego.plectrum.data.cache

import io.reactivex.Completable
import io.reactivex.Observable

interface Cache<Key : Any, Value : Any> {
    // replacement Deferred coroutines?
    // Deferred<Value?>, Deferred<Unit>
    fun get(key: Key): Observable<Value>
    fun put(key: Key, value: Value): Completable
    fun evict(key: Key): Completable
    fun evictAll(): Completable
}