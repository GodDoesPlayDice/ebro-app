package com.example.ebroapp.utils

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.PowerManager
import com.example.ebroapp.domain.OnPlayerStateChangeListener
import com.example.ebroapp.domain.entity.song.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class PlayerDelegate(private val context: Context) {
    private val player by lazy { MediaPlayer() }
    private var observer: MediaObserver? = null
    private val playList = getMusicList(context)
    var currentSong: Song = playList.first()

    fun playPauseMusic(isPlay: Boolean) {
        if (isPlay) playMusic() else pauseMusic()
    }

    fun stopMusic() {
        player.stop()
        setDataSource(currentSong.dataSource)
    }

    fun isPlaying() = player.isPlaying

    fun initPlayer() {
        player.apply {
            setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            val afd = context.assets.openFd(currentSong.dataSource)
            setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            prepare()
        }
    }

    private fun setDataSource(fileName: String) {
        player.apply {
            reset()
            val afd = context.assets.openFd(fileName)
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

    fun nextSong(song: Song) {
        currentSong = song
        stopMusic()
        playMusic()
    }

    fun nextSong() {
        val nextSongIndex = playList.indexOf(currentSong) + 1
        currentSong = if (nextSongIndex == playList.size) {
            playList[0]
        } else {
            playList[nextSongIndex]
        }
        stopMusic()
        playMusic()
    }

    fun previousSong() {
        val previousSongIndex = playList.indexOf(currentSong) - 1
        currentSong = if (previousSongIndex == -1) {
            playList[playList.size - 1]
        } else {
            playList[previousSongIndex]
        }
        stopMusic()
        playMusic()
    }

    fun setOnPlayerStateChangeListener(onPlayerStateChangeListener: OnPlayerStateChangeListener) {
        observer?.stop()
        player.setOnCompletionListener {
            observer?.stop()
            onPlayerStateChangeListener.onStateChange(0, 0)
        }
        observer = MediaObserver(onPlayerStateChangeListener)
        Thread(observer).start()
    }

    inner class MediaObserver(private val onPlayerStateChangeListener: OnPlayerStateChangeListener) :
        Runnable {
        private val stop: AtomicBoolean = AtomicBoolean(false)
        fun stop() {
            stop.set(true)
        }

        override fun run() {
            while (!stop.get()) {
                GlobalScope.launch(Dispatchers.Main) {
                    if (player.isPlaying) {
                        onPlayerStateChangeListener.onStateChange(
                            player.currentPosition / 1000,
                            player.duration / 1000
                        )
                    }
                }
                Thread.sleep(1000)
            }
        }
    }
}