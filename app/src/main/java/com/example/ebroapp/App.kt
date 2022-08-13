package com.example.ebroapp

import android.app.Application
import com.example.ebroapp.di.Injector
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Injector.initAppComponent(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}