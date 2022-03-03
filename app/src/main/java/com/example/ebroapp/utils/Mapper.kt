package com.example.ebroapp.utils

import com.example.ebroapp.domain.entity.song.Separator
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.domain.entity.song.SongListItem
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SEPARATOR

object Mapper {
    fun List<Song>.toAdapter(): List<SongListItem> {
        val items: MutableList<SongListItem> = mutableListOf()
        if (this.isNotEmpty()) {
            items.addAll(this)
            items.sortBy { !(it as Song).isFavorites }
            items.add(0, Separator("FAVORITES", TYPE_SEPARATOR))
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
        }
        return items
    }
}