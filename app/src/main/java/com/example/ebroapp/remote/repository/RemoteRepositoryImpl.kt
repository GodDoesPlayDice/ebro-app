package com.example.ebroapp.remote.repository

import com.example.ebroapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RemoteRepositoryImpl : RemoteRepository {

    private val remoteService: RemoteService
    private var client: OkHttpClient

    init {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .addNetworkInterceptor(logging)
            .build()

        remoteService = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RemoteService::class.java)
    }

    override suspend fun getWeatherFull(lat: Double?, lon: Double?) =
        remoteService.getWeatherFull(lat, lon)
}