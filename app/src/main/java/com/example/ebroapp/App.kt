package com.example.ebroapp

import android.app.Application
import com.example.ebroapp.utils.PlayerUtil

class App : Application() {

    val player by lazy { PlayerUtil(applicationContext) }

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this

        player.setPlayList(applicationContext)
    }

    companion object {
        private var APPLICATION: App? = null
        fun get(): App {
            return APPLICATION!!
        }
    }
}