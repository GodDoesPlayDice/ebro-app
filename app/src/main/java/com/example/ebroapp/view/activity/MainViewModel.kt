package com.example.ebroapp.view.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.ebroapp.utils.music.Player
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val player: Player
) : ViewModel() {

    fun setPlayList(context: Context) {
        player.setPlayList(context)
    }

    fun pauseMusic() {
        player.pauseMusic()
    }
}
