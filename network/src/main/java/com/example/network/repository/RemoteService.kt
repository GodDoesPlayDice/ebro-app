package com.example.network.repository

import com.example.network.entity.FullWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("data/2.5/onecall")
    suspend fun getWeatherFull(
        @Query("lon") longitude: Double?,
        @Query("lat") latitude: Double?,
        @Query("units") units: String = UNITS,
        @Query("appid") appId: String = TOKEN,
        @Query("exclude") exclude: String = EXCLUDE
    ): FullWeather

    companion object {
        private const val UNITS = "metric"
        private const val TOKEN = "7e72084d8d1fd4939c577cd7394bcac5"
        private const val EXCLUDE = "hourly,minutely,alerts"
    }
}