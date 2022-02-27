package com.example.ebroapp.domain

import com.example.ebroapp.domain.entity.song.Song

interface OnPlayerStateChangeListener {
    fun onStateChange(progress: Int, duration: Int)
}