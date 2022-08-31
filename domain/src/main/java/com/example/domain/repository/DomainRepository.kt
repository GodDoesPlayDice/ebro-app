package com.example.domain.repository

import com.example.domain.entity.LocalPoint
import com.example.domain.entity.Song

interface DomainRepository {

    fun setSongs(songs: List<Song>)

    fun getSongs(): List<Song>

    fun isFavoriteSong(id: Long): Boolean

    fun setSongIsFavorite(id: Long, isFavorite: Boolean)

    fun addAddress(address: String)

    fun getAddresses(): List<String>

    fun setOnAddressesChangeListener(onAddressChangeListener: (String) -> Unit)

    fun addCurrentLocation(point: LocalPoint)

    fun getCurrentLocation(): LocalPoint?

    fun addDestinationLocation(point: LocalPoint)

    fun removeDestinationLocation()

    fun getDestinationLocation(): LocalPoint?
}
