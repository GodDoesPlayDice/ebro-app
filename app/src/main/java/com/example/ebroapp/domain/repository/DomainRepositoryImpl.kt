package com.example.ebroapp.domain.repository

import com.example.ebroapp.domain.entity.song.Song
import com.mapbox.geojson.Point


class DomainRepositoryImpl : DomainRepository {

    private val preferenceManager = PreferenceManager.get()

    override fun setSongs(songs: List<Song>) = preferenceManager.setSongs(songs)

    override fun getSongs(): List<Song> = preferenceManager.getSongs()

    override fun isFavoriteSong(id: Long) = preferenceManager.isFavoriteSong(id)

    override fun setSongIsFavorite(id: Long, isFavorite: Boolean) =
        preferenceManager.setFavoriteSong(id, isFavorite)

    override fun addAddress(address: String) = preferenceManager.addAddress(address)

    override fun getAddresses(): List<String> = preferenceManager.getAddresses()

    override fun setOnAddressesChangeListener(onAddressChangeListener: (String) -> Unit) =
        preferenceManager.setOnAddressesChangeListener(onAddressChangeListener)

    override fun addCurrentLocation(point: Point) =
        preferenceManager.addCurrentLocation(point)

    override fun getCurrentLocation(): Point? = preferenceManager.getCurrentLocation()

    override fun addDestinationLocation(point: Point) =
        preferenceManager.addDestinationLocation(point)

    override fun removeDestinationLocation() =
        preferenceManager.removeDestinationLocation()

    override fun getDestinationLocation(): Point? = preferenceManager.getDestinationLocation()
}
