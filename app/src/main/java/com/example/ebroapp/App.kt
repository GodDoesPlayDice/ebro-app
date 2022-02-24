package com.example.ebroapp

import android.app.Application
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.PowerManager
import android.util.Log

class App : Application() {

    val player by lazy { MediaPlayer() }

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this

        initPlayer()
    }

    private fun initPlayer() {
        player.apply {
            setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            try {
                val afd = assets.openFd("drake_elevate.mp3")
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                prepareAsync()
            } catch (e: Exception) {
                Log.e("MUSIC SERVICE", "Error setting data source", e)
            }
        }
    }

    fun playPauseMusic() {
        if (player.isPlaying) {
            player.pause()
        } else {
            player.isLooping = true
            player.start()
        }
    }

    companion object {
        private var APPLICATION: App? = null
        fun get(): App {
            return APPLICATION!!
        }
    }
}