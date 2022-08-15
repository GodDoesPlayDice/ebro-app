package com.example.network.repository

import com.example.network.entity.FullWeather

interface RemoteRepository {

    suspend fun getWeatherFull(lon: Double?, lat: Double?): FullWeather
}
