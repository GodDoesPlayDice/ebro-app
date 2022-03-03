package com.example.ebroapp.domain

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

    fun isFavoriteSong(id: Long): Boolean

    fun setSongIsFavorite(id: Long, isFavorite: Boolean)
}