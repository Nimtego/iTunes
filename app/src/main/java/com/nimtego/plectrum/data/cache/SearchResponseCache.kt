package com.nimtego.plectrum.data.cache

import android.util.Log
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

class SearchResponseCache @Inject constructor() : Cache<String, PopularResponse> {

    private val TAG = this.javaClass.canonicalName
    private val cache = ConcurrentHashMap<String, PopularResponse>()

    override fun get(key: String): Observable<PopularResponse> {
        Log.v(TAG, "Get from cache. Key - $key")
        return Observable.create { emitter ->
            cache[key]?.let {
                emitter.onNext(it)
            }
            emitter.onComplete()
        }
    }

    override fun put(key: String, value: PopularResponse): Completable {
        return Completable.create { cache[key] = value }
    }

    override fun evict(key: String): Completable {
        Log.v(TAG, "Clear cache")
        return Completable.create { cache.remove(key) }
    }

    override fun evictAll(): Completable {
        return Completable.create { cache.clear() }
    }
    override fun getAllValue(): Observable<List<PopularResponse>> {
        return Observable.fromArray(this.cache.values.toList())
    }

}