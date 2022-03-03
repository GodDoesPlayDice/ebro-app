package com.example.ebroapp.utils

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.PowerManager
import com.example.ebroapp.domain.entity.song.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class PlayerDelegate(private val context: Context) {
    private val player by lazy { MediaPlayer() }
    private var observer: MediaObserver? = null
    var currentSong: Song? = getMusicList(context).firstOrNull()

    fun playPauseMusic(isPlay: Boolean) {
        if (isPlay) playMusic() else pauseMusic()
    }

    fun stopMusic() {
        currentSong?.let {
            player.stop()
            setDataSource(it.contentUri)
        }

    }

    fun isPlaying() = player.isPlaying

    fun initPlayer() {
        currentSong?.let {
            player.apply {
                setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
                setAudioStreamType(AudioManager.STREAM_MUSIC);
                setDataSource(context, it.contentUri)
                prepare()
            }
        }
    }

    private fun setDataSource(uri: Uri) {
        player.apply {
            reset()
            setDataSource(context,uri)
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
        val playList = getMusicList(context)
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
        val playList = getMusicList(context)
        val previousSongIndex = playList.indexOf(currentSong) - 1
        currentSong = if (previousSongIndex == -1) {
            playList[playList.size - 1]
        } else {
            playList[previousSongIndex]
        }
        stopMusic()
        playMusic()
    }

    fun setOnPlayerStateChangeListener(listener :(Int, Int) -> Unit) {
        observer?.stop()
        player.setOnCompletionListener {
            observer?.stop()
            listener(0, 0)
        }
        observer = MediaObserver(listener)
        Thread(observer).start()
    }

    inner class MediaObserver(private val listener :(Int, Int) -> Unit) :
        Runnable {
        private val stop: AtomicBoolean = AtomicBoolean(false)
        fun stop() {
            stop.set(true)
        }

        override fun run() {
            while (!stop.get()) {
                GlobalScope.launch(Dispatchers.Main) {
                    if (player.isPlaying) {
                        listener(
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