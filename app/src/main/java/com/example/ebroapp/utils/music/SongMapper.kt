package com.example.ebroapp.utils.music

import com.example.domain.entity.Separator
import com.example.domain.entity.Song
import com.example.domain.entity.SongListItem
import com.example.domain.entity.SongListItem.Companion.TYPE_SEPARATOR

object SongMapper {

    private const val FAVORITES = "FAVORITES"
    private const val TOP_STATIONS = "TOP STATIONS"

    fun List<Song>.toAdapter(): List<SongListItem> {
        val items: MutableList<SongListItem> = mutableListOf()
        if (this.isNotEmpty()) {
            items.addAll(this)
            items.sortBy { !(it as Song).isFavorites }
            items.add(0, Separator(FAVORITES, TYPE_SEPARATOR))
            if (items.size > 2) {
                var i = 1
                while (i < items.size) {
                    if (!(items[i] as Song).isFavorites) {
                        items.add(i, Separator(TOP_STATIONS, TYPE_SEPARATOR))
                        break
                    }
                    i++
                }
            }
        }
        if (items.firstOrNull { it is Song && !it.isFavorites } == null) {
            items.add(Separator(TOP_STATIONS, TYPE_SEPARATOR))
        }
        return items
    }
}
