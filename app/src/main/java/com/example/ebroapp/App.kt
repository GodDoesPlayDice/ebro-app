package com.example.ebroapp

import android.app.Application
import com.example.ebroapp.utils.PlayerDelegate


class App : Application() {

    val playerDelegate by lazy { PlayerDelegate(applicationContext) }

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this

        playerDelegate.initPlayer()
    }

    companion object {
        private var APPLICATION: App? = null
        fun get(): App {
            return APPLICATION!!
        }
    }
}