package com.example.ebroapp

import android.app.Application
import com.example.ebroapp.domain.DomainRepository
import com.example.ebroapp.utils.PlayerUtil
import com.example.ebroapp.utils.getMusicList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class App : Application() {

    val player by lazy { PlayerUtil(applicationContext) }

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this

        GlobalScope.launch(Dispatchers.IO) {
            DomainRepository.obtain().setSongs(getMusicList(this@App))
            withContext(Dispatchers.Main) {
                player.init()
            }
        }
    }

    companion object {
        private var APPLICATION: App? = null
        fun get(): App {
            return APPLICATION!!
        }
    }
}