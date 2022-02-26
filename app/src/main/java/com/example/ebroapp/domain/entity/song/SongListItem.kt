package com.example.ebroapp.domain.entity.song

abstract class SongListItem {
    abstract val type: Int

    companion object {
        const val TYPE_SONG = 0
        const val TYPE_SEPARATOR = 1
    }
}