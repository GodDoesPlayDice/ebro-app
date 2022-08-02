package com.example.ebroapp.di.module.data

import com.example.ebroapp.domain.repository.DomainRepository
import com.example.ebroapp.domain.repository.DomainRepositoryImpl
import com.example.ebroapp.remote.repository.RemoteRepository
import com.example.ebroapp.remote.repository.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindDomainRepository(repository: DomainRepositoryImpl): DomainRepository

    @Binds
    @Singleton
    fun bindRemoteRepository(repository: RemoteRepositoryImpl): RemoteRepository
}