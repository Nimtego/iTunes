package com.nimtego.plectrum.data.cache

import android.content.Context
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.domain.executor.ThreadExecutor
import io.reactivex.Observable
import java.io.File
import java.lang.Exception
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject


class PopularResponseCache @Inject constructor(
        private val context: Context,
        private val serializer: Serializer,
        private val fileManager: FileManager,
        private val threadPoolExecutor: ThreadExecutor
) : Cache<String, PopularResponse> {

    private val SETTINGS_FILE_NAME = "popular_response_cache"
    private val SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update"

    private val DEFAULT_FILE_NAME = "popular_response_cache_file"
    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private val cacheDir: File = this.context.cacheDir
    private val cache = ConcurrentHashMap<String, PopularResponse>()

    override fun get(key: String): Observable<PopularResponse> {
        return Observable.create<PopularResponse> { emitter ->
            cache[key]?.let { emitter.onNext(it) }
            emitter.onComplete()
        }
    }

    override fun put(key: String, value: PopularResponse) {
        if (!isCached(key)) {
            cache[key] = value
        }
    }

    override fun isCached(key: String): Boolean {
       return this.cache.containsKey(key)
    }

    override fun isExpired(): Boolean {
//        val currentTime = System.currentTimeMillis()
//        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
//
//        val expired = currentTime - lastUpdateTime > EXPIRATION_TIME
//
//        if (expired) {
//            this.evictAll()
//        }
//
//        return expired
        return false
    }

    override fun evictAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun get(key: String): Observable<PopularResponse> {
//        return Observable.create<PopularResponse> { emitter ->
//            val userEntityFile = this@PopularResponseCache.buildFile(key)
//            val fileContent = this@PopularResponseCache.fileManager.readFileContent(userEntityFile)
//            val userEntity: PopularResponse = this@PopularResponseCache.serializer.deserialize(fileContent, PopularResponse::class.java)
//
//            emitter.onNext(userEntity)
//            emitter.onComplete()
//        }
//    }
//
//    override fun put(key: String, value: PopularResponse) {
//        val userEntityFile = this.buildFile(key)
//        if (!isCached(key)) {
//            val jsonString = this.serializer.serialize(value, PopularResponse::class.java)
//            this.executeAsynchronously(CacheWriter(this.fileManager, userEntityFile, jsonString))
//            setLastCacheUpdateTimeMillis()
//        }
//    }
//
//    private fun executeAsynchronously(runnable: Runnable) {
//        this.threadPoolExecutor.execute(runnable)
//    }
//
//    private fun setLastCacheUpdateTimeMillis() {
//        val currentMillis = System.currentTimeMillis()
//        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
//                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis)
//    }
//
//    private fun getLastCacheUpdateTimeMillis(): Long {
//        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
//                SETTINGS_KEY_LAST_CACHE_UPDATE)
//    }
//
//    override fun isCached(key: String): Boolean {
//        val entityCache = this.buildFile(key)
//        return this.fileManager.exists(entityCache)
//    }
//
//    override fun isExpired(): Boolean {
//        val currentTime = System.currentTimeMillis()
//        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
//
//        val expired = currentTime - lastUpdateTime > EXPIRATION_TIME
//
//        if (expired) {
//            this.evictAll()
//        }
//
//        return expired
//    }
//
//    override fun evictAll() {
//        this.executeAsynchronously(CacheEvictor(this.fileManager, this.cacheDir))
//    }
//
//    private fun buildFile(id: String): File {
//        val fileNameBuilder = StringBuilder()
//        fileNameBuilder.append(this.cacheDir.path)
//        fileNameBuilder.append(File.separator)
//        fileNameBuilder.append(DEFAULT_FILE_NAME)
//        fileNameBuilder.append(id)
//
//        return File(fileNameBuilder.toString())
//    }
//
//    private class CacheWriter internal constructor(private val fileManager: FileManager,
//                                                   private val fileToWrite: File,
//                                                   private val fileContent: String) : Runnable {
//
//        override fun run() {
//            this.fileManager.writeToFile(fileToWrite, fileContent)
//        }
//    }
//
//    private class CacheEvictor internal constructor(private val fileManager: FileManager,
//                                                    private val cacheDir: File) : Runnable {
//
//        override fun run() {
//            this.fileManager.clearDirectory(this.cacheDir)
//        }
//    }
}