package com.nimtego.plectrum.data.cache

import android.content.Context
import com.nimtego.plectrum.data.model.rss_itunes.PopularResponse
import com.nimtego.plectrum.domain.executor.ThreadExecutor
import io.reactivex.Observable
import java.io.File


class DashBoardEntityCache<E : PopularResponse> : CacheK<E> {

    private val SETTINGS_FILE_NAME = "dash_board_cache_1"
    private val SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update"

    private val DEFAULT_FILE_NAME = "dash_board_cache"
    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private val context: Context
    private val serializer: Serializer
    private val fileManager: FileManager
    private val cacheDir: File
    private val threadPoolExecutor: ThreadExecutor

    constructor(context: Context,
                serializer: Serializer,
                fileManager: FileManager,
                threadPoolExecutor: ThreadExecutor) {
        this.context = context.applicationContext
        this.cacheDir = this.context.cacheDir
        this.serializer = serializer
        this.fileManager = fileManager
        this.threadPoolExecutor = threadPoolExecutor
    }

    override fun get(id: String): Observable<E> {
        return Observable.create<E> { emitter ->
            val userEntityFile = this@DashBoardEntityCache.buildFile(id)
            val fileContent = this@DashBoardEntityCache.fileManager.readFileContent(userEntityFile)
            val userEntity: PopularResponse = this@DashBoardEntityCache.serializer.deserialize(fileContent, PopularResponse::class.java)

            if (userEntity != null) {
                emitter.onNext(userEntity as E)
                emitter.onComplete()
            } else {
                emitter.onError(EntityNotFoundException())
            }
        }
    }

    override fun put(entity: E) {
        if (entity != null) {
            val userEntityFile = this.buildFile(entity.feed.id)
            if (!isCached(entity.feed.id)) {
                val jsonString = this.serializer.serialize(entity, PopularResponse::class.java)
                this.executeAsynchronously(CacheWriter(this.fileManager, userEntityFile, jsonString))
                setLastCacheUpdateTimeMillis()
            }
        }
    }

    private fun executeAsynchronously(runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private fun setLastCacheUpdateTimeMillis() {
        val currentMillis = System.currentTimeMillis()
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis)
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE)
    }

    override fun isCached(id: String): Boolean {
        val entityCache = this.buildFile(id)
        return this.fileManager.exists(entityCache)
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()

        val expired = currentTime - lastUpdateTime > EXPIRATION_TIME

        if (expired) {
            this.evictAll()
        }

        return expired
    }

    override fun evictAll() {
        this.executeAsynchronously(CacheEvictor(this.fileManager, this.cacheDir))
    }

    private fun buildFile(id: String): File {
        val fileNameBuilder = StringBuilder()
        fileNameBuilder.append(this.cacheDir.path)
        fileNameBuilder.append(File.separator)
        fileNameBuilder.append(DEFAULT_FILE_NAME)
        fileNameBuilder.append(id)

        return File(fileNameBuilder.toString())
    }

    private class CacheWriter internal constructor(private val fileManager: FileManager,
                                                   private val fileToWrite: File,
                                                   private val fileContent: String) : Runnable {

        override fun run() {
            this.fileManager.writeToFile(fileToWrite, fileContent)
        }
    }

    private class CacheEvictor internal constructor(private val fileManager: FileManager,
                                                    private val cacheDir: File) : Runnable {

        override fun run() {
            this.fileManager.clearDirectory(this.cacheDir)
        }
    }
}