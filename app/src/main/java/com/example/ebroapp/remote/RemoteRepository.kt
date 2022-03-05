package com.example.ebroapp.remote

import com.example.ebroapp.remote.entity.weather.CurrentWeather
import com.example.ebroapp.remote.entity.weather.FullWeather
import io.reactivex.Single

interface RemoteRepository {

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

    fun getCurrentWeather(): Single<CurrentWeather>

    fun getWeatherFull(): Single<FullWeather>
}