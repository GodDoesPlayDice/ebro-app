package com.example.ebroapp.domain

import android.content.Context
import com.example.ebroapp.App

class PreferenceManager private constructor() {

    fun setFavoriteSong(id: Long, isFavorite: Boolean) {
        preference.edit()
            .putBoolean(SONG_IS_FAVORITE.format(id), isFavorite)
            .apply()
    }

    fun isFavoriteSong(id: Long): Boolean {
        return preference.getBoolean(SONG_IS_FAVORITE.format(id), false)
    }

    companion object {
        private val instance = PreferenceManager()
        private const val APP_PREFERENCES = "APP_PREFERENCES"
        private const val SONG_IS_FAVORITE = "SONG_IS_FAVORITE_%d"
        private val preference =
            App.get().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        fun get(): PreferenceManager {
            return instance
        }
    }
}