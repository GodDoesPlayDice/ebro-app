package com.example.ebroapp.di.module.domain

import com.example.domain.repository.DomainRepository
import com.example.domain.repository.DomainRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Binds
    @Singleton
    fun bindDomainRepository(repository: DomainRepositoryImpl): DomainRepository
}