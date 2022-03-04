package com.example.ebroapp.domain.entity.song

import android.net.Uri

class Song(
    val id: Long,
    val uri: Uri,
    val name: String,
    val album: String,
    val singer: String,
    override val type: Int,
    var isFavorites: Boolean = false
) : SongListItem() {

    override fun equals(other: Any?): Boolean {
        if (other is Song) {
            return other.uri == uri && other.name == name && other.album == album && other.singer == singer
        }
        return false
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + uri.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + album.hashCode()
        result = 31 * result + singer.hashCode()
        result = 31 * result + isFavorites.hashCode()
        return result
    }
}
