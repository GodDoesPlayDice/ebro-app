package com.example.ebroapp.remote.repository

import com.example.ebroapp.remote.entity.weather.FullWeather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


object WeatherProperties {
    const val units = "metric"
    const val token = "7e72084d8d1fd4939c577cd7394bcac5"
    const val exclude = "hourly,minutely,alerts"
}

interface RemoteService {
    @GET("data/2.5/onecall")
    suspend fun getWeatherFull(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("units") units: String = WeatherProperties.units,
        @Query("appid") appid: String = WeatherProperties.token,
        @Query("exclude") exclude: String = WeatherProperties.exclude
    ): FullWeather
}