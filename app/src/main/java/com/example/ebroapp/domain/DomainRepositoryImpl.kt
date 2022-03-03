package com.example.ebroapp.domain

class DomainRepositoryImpl : DomainRepository {
    private val preferenceManager = PreferenceManager.get()

    override fun isFavoriteSong(id: Long) = preferenceManager.isFavoriteSong(id)

    override fun setSongIsFavorite(id: Long, isFavorite: Boolean) =
        preferenceManager.setFavoriteSong(id, isFavorite)
}
