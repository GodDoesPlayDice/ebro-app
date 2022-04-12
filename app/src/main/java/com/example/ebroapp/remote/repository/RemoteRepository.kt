package com.example.ebroapp.remote.repository

import com.example.ebroapp.remote.entity.weather.CurrentWeather
import com.example.ebroapp.remote.entity.weather.FullWeather
import io.reactivex.Single


interface RemoteRepository {

    fun getCurrentWeather(): Single<CurrentWeather>

    fun getWeatherFull(lat: Double?, lon: Double?): Single<FullWeather>

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