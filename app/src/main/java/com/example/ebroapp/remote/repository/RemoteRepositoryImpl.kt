package com.example.ebroapp.remote.repository

import javax.inject.Inject


class RemoteRepositoryImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteRepository {

    override suspend fun getWeatherFull(lat: Double?, lon: Double?) =
        remoteService.getWeatherFull(lat, lon)
}