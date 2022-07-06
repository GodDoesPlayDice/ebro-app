package com.example.ebroapp.remote.repository

import com.example.ebroapp.remote.entity.weather.FullWeather


interface RemoteRepository {

    suspend fun getWeatherFull(lat: Double?, lon: Double?): FullWeather

    companion object {
        @Volatile
        private var instance: RemoteRepository? = null

        fun obtain(): RemoteRepository {
            instance = instance ?: synchronized(this) {
                RemoteRepositoryImpl()
            }
            return instance!!
        }
    }
}