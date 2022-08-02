package com.example.ebroapp.utils.provider

import com.example.ebroapp.remote.repository.RemoteService

interface RetrofitProvider {
    fun provideRetrofit(): RemoteService
}
