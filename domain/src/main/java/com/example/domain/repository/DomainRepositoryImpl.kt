package com.example.domain.repository

import android.content.SharedPreferences
import com.example.domain.entity.LocalPoint
import com.example.domain.entity.Song
import com.google.gson.Gson
import javax.inject.Inject


class DomainRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences,
    private val gson: Gson
) : DomainRepository {

    private var onAddressChangeListener: ((String) -> Unit)? = null

    override fun setSongs(songs: List<Song>) {
        if (songs.isNotEmpty()) {
            preferences.edit().putString(SONGS_LIST, gson.toJson(songs)).apply()
        }
    }

    override fun getSongs(): List<Song> {
        val json = preferences.getString(SONGS_LIST, "")
        return if (json != "") {
            gson.fromJson(json, Array<Song>::class.java).toList()
                .onEach { it.isFavorites = isFavoriteSong(it.id) }
        } else {
            listOf()
        }
    }

    override fun isFavoriteSong(id: Long): Boolean =
        preferences.getBoolean(SONG_IS_FAVORITE.format(id), true)

    override fun setSongIsFavorite(id: Long, isFavorite: Boolean) {
        preferences.edit().putBoolean(SONG_IS_FAVORITE.format(id), isFavorite).apply()
    }

    override fun addAddress(address: String) {
        val list = getAddresses().toMutableList()
        list.add(address)
        preferences.edit().putString(ADDRESSES_LIST, gson.toJson(list.toList())).apply()
        onAddressChangeListener?.invoke(address)
    }

    override fun getAddresses(): List<String> {
        val json = preferences.getString(ADDRESSES_LIST, "")
        return if (json != "") {
            gson.fromJson(json, Array<String>::class.java).toList()
        } else {
            listOf()
        }
    }

    override fun setOnAddressesChangeListener(onAddressChangeListener: (String) -> Unit) {
        this.onAddressChangeListener = onAddressChangeListener
    }

    override fun addCurrentLocation(point: LocalPoint) {
        preferences.edit().putString(CURRENT_LOCATION, gson.toJson(point)).apply()
    }

    override fun getCurrentLocation(): LocalPoint? {
        val json = preferences.getString(CURRENT_LOCATION, "")
        return if (json != "") {
            gson.fromJson(json, LocalPoint::class.java)
        } else {
            null
        }
    }

    override fun addDestinationLocation(point: LocalPoint) {
        preferences.edit().putString(DESTINATION_LOCATION, gson.toJson(point)).apply()
    }


    override fun removeDestinationLocation() {
        preferences.edit().putString(DESTINATION_LOCATION, "").apply()
    }

    override fun getDestinationLocation(): LocalPoint? {
        val json = preferences.getString(DESTINATION_LOCATION, "")
        return if (json != "") {
            gson.fromJson(json, LocalPoint::class.java)
        } else {
            null
        }
    }

    companion object {
        private const val SONG_IS_FAVORITE = "SONG_IS_FAVORITE_%d"
        private const val SONGS_LIST = "SONGS_LIST"
        private const val ADDRESSES_LIST = "ADDRESSES_LIST"
        private const val CURRENT_LOCATION = "CURRENT_LOCATION"
        private const val DESTINATION_LOCATION = "DESTINATION_LOCATION"
    }
}
