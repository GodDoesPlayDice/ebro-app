package com.example.network.provider

import com.example.network.repository.RemoteService

interface RetrofitProvider {
    fun provideRetrofit(): RemoteService
}
