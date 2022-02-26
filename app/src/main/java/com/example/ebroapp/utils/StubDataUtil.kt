package com.example.ebroapp.utils

import android.content.Context
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SONG

fun getMusicList(context: Context): List<Song> {
    val songs: MutableList<Song> = mutableListOf()
    var i = 0
    while (i < 30) {
        val rawId = context.resources.getIdentifier("album_cover_$i", "raw", context.packageName)
        songs.add(Song(rawId, "Track $i", "Album $i", "Singer $i", i < 20, TYPE_SONG))
        i++
    }
    return songs
}