package com.example.ebroapp.view.fragment.muisc

import androidx.lifecycle.ViewModel
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.utils.Player
import javax.inject.Inject

class MusicViewModel @Inject constructor(
    private val player: Player
) : ViewModel() {

    fun setOnPlayerStateChangeListener(listener: (Int, Int) -> Unit) {
        player.setOnPlayerStateChangeListener { progress, duration ->
            listener.invoke(progress, duration)
        }
    }

    fun isPlaying() = player.isPlaying()

    fun playPauseMusic(isChecked: Boolean) {
        player.playPauseMusic(isChecked)
    }

    fun nextSong() {
        player.nextSong()
    }

    fun previousSong() {
        player.previousSong()
    }

    fun getCurrentSong(listener: (Song) -> Unit) {
        player.currentSong?.let { listener.invoke(it) }
    }
}
