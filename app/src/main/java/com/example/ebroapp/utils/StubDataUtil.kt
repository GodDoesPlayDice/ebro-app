package com.example.ebroapp.utils

import com.example.ebroapp.R
import com.example.ebroapp.domain.entity.Song

fun getMusicList(): List<Song> {
    val songs: MutableList<Song> = mutableListOf()
    var i = 0
    while (i < 100) {
        songs.add(Song(R.drawable.ic_song_stub, "Billy Jean", "Thriller", "Michael Jackson"))
        i++
    }
    return songs
}