package com.example.ebroapp.domain.repository

import com.example.ebroapp.domain.entity.song.Song
import com.mapbox.geojson.Point


interface DomainRepository {

    fun setSongs(songs: List<Song>)

    fun getSongs(): List<Song>

    fun isFavoriteSong(id: Long): Boolean

    fun setSongIsFavorite(id: Long, isFavorite: Boolean)

    fun addAddress(it: String)

    fun getAddresses(): List<String>

    fun setOnAddressesChangeListener(onAddressChangeListener: (String) -> Unit)

    fun addCurrentLocation(point: Point)

    fun getCurrentLocation(): Point?

    fun addDestinationLocation(point: Point)

    fun removeDestinationLocation()

    fun getDestinationLocation(): Point?
}