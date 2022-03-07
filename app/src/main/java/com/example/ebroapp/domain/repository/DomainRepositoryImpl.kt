package com.example.ebroapp.domain.repository

import com.example.ebroapp.domain.entity.song.Song

class DomainRepositoryImpl : DomainRepository {
    private val preferenceManager = PreferenceManager.get()

    override fun setSongs(songs: List<Song>) = preferenceManager.setSongs(songs)

    override fun getSongs(): List<Song> = preferenceManager.getSongs()

    override fun isFavoriteSong(id: Long) = preferenceManager.isFavoriteSong(id)

    override fun setSongIsFavorite(id: Long, isFavorite: Boolean) =
        preferenceManager.setFavoriteSong(id, isFavorite)

    override fun addAddress(it: String) = preferenceManager.addAddress(it)

    override fun getAddresses(): List<String> = preferenceManager.getAddresses()

    override fun setOnAddressesChangeListener(onAddressChangeListener: (List<String>) -> Unit) =
        preferenceManager.setOnAddressesChangeListener(onAddressChangeListener)
}
