package com.example.ebroapp

import android.app.Application
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.PowerManager

class App : Application() {

    private val player by lazy { MediaPlayer() }

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this

        initPlayer()
    }

    fun playPauseMusic() {
        if (player.isPlaying) {
            player.pause()
        } else {
            player.start()
        }
    }

    fun stopMusic() {
        player.stop()
        setDataSource("drake_elevate.mp3")
    }

    fun isPlaying() = player.isPlaying

    private fun initPlayer() {
        player.apply {
            setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            val afd = assets.openFd("drake_elevate.mp3")
            setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            prepare()
        }
    }

    private fun setDataSource(fileName: String) {
        player.apply {
            reset()
            val afd = assets.openFd(fileName)
            setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            prepare()
        }
    }

    fun playMusic() {
        if (!player.isPlaying) {
            player.start()
        }
    }

    fun pauseMusic() {
        if (player.isPlaying) {
            player.pause()
        }
    }

    companion object {
        private var APPLICATION: App? = null
        fun get(): App {
            return APPLICATION!!
        }
    }
}