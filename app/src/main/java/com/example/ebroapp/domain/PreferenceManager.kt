package com.example.ebroapp.domain

import android.content.Context
import android.net.Uri
import com.example.ebroapp.App
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.utils.UriAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder


class PreferenceManager private constructor() {

    private val gson: Gson

    init {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(Uri::class.java, UriAdapter())
        builder.setPrettyPrinting()
        gson = builder.create()
    }

    fun setSongs(songs: List<Song>) {
        if (songs.isNotEmpty()) {
            preference.edit().putString(SONGS_LIST, gson.toJson(songs)).apply()
        }
    }

    fun getSongs(): List<Song> {
        val json = preference.getString(SONGS_LIST, "")
        return if (json != "") {
            gson.fromJson(json, Array<Song>::class.java).toList()
                .onEach { it.isFavorites = isFavoriteSong(it.id) }
        } else {
            listOf()
        }
    }

    fun setFavoriteSong(id: Long, isFavorite: Boolean) =
        preference.edit().putBoolean(SONG_IS_FAVORITE.format(id), isFavorite).apply()

    fun isFavoriteSong(id: Long): Boolean =
        preference.getBoolean(SONG_IS_FAVORITE.format(id), true)

    companion object {
        private val instance = PreferenceManager()
        private const val APP_PREFERENCES = "APP_PREFERENCES"
        private const val SONG_IS_FAVORITE = "SONG_IS_FAVORITE_%d"
        private const val SONGS_LIST = "SONGS_LIST"
        private val preference =
            App.get().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        fun get(): PreferenceManager {
            return instance
        }
    }
}