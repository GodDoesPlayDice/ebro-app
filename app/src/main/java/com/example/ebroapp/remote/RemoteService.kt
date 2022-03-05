package com.example.ebroapp.remote

import com.example.ebroapp.remote.entity.weather.CurrentWeather
import com.example.ebroapp.remote.entity.weather.FullWeather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

object WeatherProperties {
    const val lat = 41.390205
    const val lon = 2.154007
    const val units = "metric"
    const val token = "7e72084d8d1fd4939c577cd7394bcac5"
    const val exclude = "hourly,minutely,alerts"
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