package com.example.ebroapp.utils.music

import android.graphics.Bitmap
import android.util.LruCache

object CacheUtil {

    private val maxMemory: Int = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    private val cacheSize = maxMemory / 8

    fun addBitmapToMemoryCache(key: String, bitmap: Bitmap?) {
        if (bitmap != null) {
            if (getBitmapFromMemCache(key) == null) {
                memoryCache.put(key, bitmap)
            }
        }
    }

    fun getBitmapFromMemCache(key: String): Bitmap? {
        return memoryCache.get(key)
    }

    private val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String?, value: Bitmap): Int {
            return value.byteCount / 1024
        }
    }
}