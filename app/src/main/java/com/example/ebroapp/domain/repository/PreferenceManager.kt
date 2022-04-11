package com.example.ebroapp.domain.repository

import android.content.Context
import android.net.Uri
import com.example.ebroapp.App
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.utils.UriAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mapbox.geojson.Point


class PreferenceManager private constructor() {

    private val gson: Gson
    private var onAddressChangeListener: ((String) -> Unit)? = null

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

    fun addAddress(address: String) {
        val list = getAddresses().toMutableList()
        list.add(address)
        preference.edit().putString(ADDRESSES_LIST, gson.toJson(list.toList())).apply()
        onAddressChangeListener?.invoke(address)
    }

    fun getAddresses(): List<String> {
        val json = preference.getString(ADDRESSES_LIST, "")
        return if (json != "") {
            gson.fromJson(json, Array<String>::class.java).toList()
        } else {
            listOf()
        }
    }

    fun setOnAddressesChangeListener(onAddressChangeListener: (String) -> Unit) {
        this.onAddressChangeListener = onAddressChangeListener
    }

    fun addCurrentLocation(point: Point) {
        preference.edit().putString(CURRENT_LOCATION, gson.toJson(point)).apply()
    }

    fun getCurrentLocation(): Point? {
        val json = preference.getString(CURRENT_LOCATION, "")
        return if (json != "") {
            gson.fromJson(json, Point::class.java)
        } else {
            null
        }
    }

    fun addDestinationLocation(point: Point) {
        preference.edit().putString(DESTINATION_LOCATION, gson.toJson(point)).apply()
    }


    fun removeDestinationLocation() {
        preference.edit().putString(DESTINATION_LOCATION, "").apply()
    }

    fun getDestinationLocation(): Point? {
        val json = preference.getString(DESTINATION_LOCATION, "")
        return if (json != "") {
            gson.fromJson(json, Point::class.java)
        } else {
            null
        }
    }

    companion object {
        private val instance = PreferenceManager()
        private const val APP_PREFERENCES = "APP_PREFERENCES"
        private const val SONG_IS_FAVORITE = "SONG_IS_FAVORITE_%d"
        private const val SONGS_LIST = "SONGS_LIST"
        private const val ADDRESSES_LIST = "ADDRESSES_LIST"
        private const val CURRENT_LOCATION = "CURRENT_LOCATION"
        private const val DESTINATION_LOCATION = "DESTINATION_LOCATION"
        private val preference =
            App.get().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        fun get(): PreferenceManager {
            return instance
        }
    }
}