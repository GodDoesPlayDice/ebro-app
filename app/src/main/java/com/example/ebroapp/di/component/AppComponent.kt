package com.example.ebroapp.di.component

import com.example.ebroapp.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : IAppComponent