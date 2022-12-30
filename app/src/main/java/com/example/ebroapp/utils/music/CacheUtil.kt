package com.example.ebroapp.utils.music

import android.graphics.Bitmap
import android.util.LruCache

object CacheUtil {

    private const val MB = 1000
    private const val B = 8

    private val maxMemory: Int = (Runtime.getRuntime().maxMemory() / MB).toInt()
    private val cacheSize = maxMemory / B
    private val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String?, value: Bitmap): Int = value.byteCount / MB
    }

    fun addBitmapToMemoryCache(key: String, bitmap: Bitmap?) {
        if (bitmap != null && getBitmapFromMemCache(key) == null) {
            memoryCache.put(key, bitmap)
        }
    }

    fun getBitmapFromMemCache(key: String): Bitmap? = memoryCache.get(key)
}
