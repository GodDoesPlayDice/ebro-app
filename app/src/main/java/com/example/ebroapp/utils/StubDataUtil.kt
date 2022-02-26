package com.example.ebroapp.utils

import android.content.Context
import com.example.ebroapp.domain.entity.song.Separator
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.domain.entity.song.SongListItem
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SEPARATOR
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SONG

fun getMusicListItems(context: Context): List<SongListItem> {
    val items: MutableList<SongListItem> = mutableListOf()
    val songs = getMusicList(context)
    if (songs.isNotEmpty()) {
        items.add(Separator("FAVORITES", TYPE_SEPARATOR))
        songs.sortedBy { it.isFavorites }
    }
    items.addAll(songs)
    if (items.size > 2) {
        var i = 2
        while (i < items.size) {
            if (!(items[i] as Song).isFavorites) {
                items.add(i, Separator("TOP STATIONS", TYPE_SEPARATOR))
                break
            }
            i++
        }
    }
    return items
}

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