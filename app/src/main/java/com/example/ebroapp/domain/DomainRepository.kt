package com.example.ebroapp.domain

import com.example.ebroapp.domain.entity.song.Song

interface DomainRepository {
    companion object {
        @Volatile
        private var instance: DomainRepository? = null

        fun obtain(): DomainRepository {
            instance = instance ?: synchronized(this) {
                DomainRepositoryImpl()
            }
            return instance!!
        }
    }

    fun setSongs(songs: List<Song>)

    fun getSongs(): List<Song>

    fun isFavoriteSong(id: Long): Boolean

    fun setSongIsFavorite(id: Long, isFavorite: Boolean)
}