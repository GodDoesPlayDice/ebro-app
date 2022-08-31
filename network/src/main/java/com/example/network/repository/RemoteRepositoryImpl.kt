package com.example.network.repository

import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteRepository {

    override suspend fun getWeatherFull(lon: Double?, lat: Double?) =
        remoteService.getWeatherFull(lon, lat)
}
