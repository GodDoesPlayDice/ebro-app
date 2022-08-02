package com.example.ebroapp.remote.entity.weather

data class FullWeather(
    val current: Current,
    val daily: List<Daily>
)

data class Current(
    val dt: Long,
    val temp: Double,
    val weather: List<Weather>,
)

data class Daily(
    val dt: Long,
    val temp: Temp
)

data class Weather(
    val icon: String
)

data class Temp(
    val eve: Double
)