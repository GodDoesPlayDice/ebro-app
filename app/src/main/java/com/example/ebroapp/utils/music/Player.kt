package com.example.ebroapp.utils.music

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.PowerManager
import com.example.domain.entity.Song
import com.example.domain.repository.DomainRepository
import com.example.ebroapp.utils.launchIO
import com.example.ebroapp.utils.launchMain
import com.example.ebroapp.utils.withMain
import kotlinx.coroutines.GlobalScope
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class Player @Inject constructor(
    private val context: Context,
    private val domainRepository: DomainRepository
) {

    var currentSong: Song? = null
    private val player by lazy { MediaPlayer() }
    private var observer: MediaObserver? = null
    private var onMusicLoadingComplete: (() -> Unit)? = null

    fun setPlayList(context: Context) {
        GlobalScope.launchIO({
            domainRepository.setSongs(getMusicList(context, domainRepository))
            withMain {
                init()
                onMusicLoadingComplete?.invoke()
            }
        }, { Timber.e(it) })
    }

    fun playPauseMusic(isPlay: Boolean) {
        if (isPlay) playMusic() else pauseMusic()
    }

    fun stopMusic() {
        currentSong?.let {
            player.stop()
            setDataSource(it.uri)
        }

    }

    fun isPlaying() = player.isPlaying

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
        val playList = domainRepository.getSongs()
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
        val playList = domainRepository.getSongs()
        val previousSongIndex = playList.indexOf(currentSong) - 1
        currentSong = if (previousSongIndex == -1) {
            playList[playList.size - 1]
        } else {
            playList[previousSongIndex]
        }
        stopMusic()
        playMusic()
    }

    fun setOnPlayerStateChangeListener(listener: (Int, Int) -> Unit) {
        observer?.stop()
        player.setOnCompletionListener {
            observer?.stop()
            listener(0, 0)
        }
        observer = MediaObserver(listener)
        Thread(observer).start()
    }


    fun setOnMusicLoadingComplete(onMusicLoadingComplete: () -> Unit) {
        this.onMusicLoadingComplete = onMusicLoadingComplete
    }

    private fun init() {
        currentSong = domainRepository.getSongs().firstOrNull()
        currentSong?.let {
            player.apply {
                setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(context, it.uri)
                prepare()
            }
        }
    }

    private fun setDataSource(uri: Uri) {
        player.apply {
            reset()
            setDataSource(context, uri)
            prepare()
        }
    }

    inner class MediaObserver(private val listener: (Int, Int) -> Unit) :
        Runnable {
        private val stop: AtomicBoolean = AtomicBoolean(false)
        fun stop() {
            stop.set(true)
        }

        override fun run() {
            while (!stop.get()) {
                GlobalScope.launchMain({
                    if (player.isPlaying) {
                        listener(
                            player.currentPosition / 1000,
                            player.duration / 1000
                        )
                    }
                }, { Timber.e(it) })
                Thread.sleep(1000)
            }
        }
    }
}