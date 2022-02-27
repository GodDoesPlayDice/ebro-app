package com.example.ebroapp.domain.entity.song

class Song(
    val albumCover: Int,
    val name: String,
    val album: String,
    val singer: String,
    var isFavorites: Boolean = false,
    override val type: Int,
    val dataSource: String = "drake_elevate.mp3"
) : SongListItem() {

    override fun equals(other: Any?): Boolean {
        if (other is Song) {
            return other.albumCover == albumCover && other.name == name && other.album == album && other.singer == singer
        }
        return false
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + albumCover.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + album.hashCode()
        result = 31 * result + singer.hashCode()
        result = 31 * result + isFavorites.hashCode()
        return result
    }
}
