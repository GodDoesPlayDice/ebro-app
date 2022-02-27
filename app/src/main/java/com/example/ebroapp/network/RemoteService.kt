package com.example.ebroapp.network

import com.example.ebroapp.models.CurrentWeather
import com.example.ebroapp.models.FullWeather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

object WeatherProperties {
    val lat = 41.390205
    val lon = 2.154007
    val units = "metric"
    val token = "7e72084d8d1fd4939c577cd7394bcac5"
    val exclude = "hourly,minutely,alerts"
}

interface RemoteService {
    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: Double = WeatherProperties.lat,
        @Query("lon") lon: Double = WeatherProperties.lon,
        @Query("units") units: String = WeatherProperties.units,
        @Query("appid") appid: String = WeatherProperties.token
    ): Single<CurrentWeather>

    // Полные данные включая текущую погоду, и погоду на ближайшие 7 дней
    @GET("data/2.5/onecall")
    fun getWeatherFull(
        @Query("lat") lat: Double = WeatherProperties.lat,
        @Query("lon") lon: Double = WeatherProperties.lon,
        @Query("units") units: String = WeatherProperties.units,
        @Query("appid") appid: String = WeatherProperties.token,
        @Query("exclude") exclude: String = WeatherProperties.exclude
    ): Single<FullWeather>
}