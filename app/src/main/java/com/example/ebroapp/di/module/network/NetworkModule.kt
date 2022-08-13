package com.example.ebroapp.di.module.network

import com.example.network.repository.RemoteRepository
import com.example.network.repository.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NetworkModule {

    @Binds
    @Singleton
    fun bindRemoteRepository(repository: RemoteRepositoryImpl): RemoteRepository
}