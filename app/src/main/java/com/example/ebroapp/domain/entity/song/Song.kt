package com.example.ebroapp.domain.entity.song

class Song(
    val albumCover: Int,
    val name: String,
    val album: String,
    val singer: String,
    var isFavorites: Boolean = false,
    override val type: Int
) : SongListItem()
