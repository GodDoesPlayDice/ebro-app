package com.example.ebroapp.domain.entity.song

data class Separator(
    val label: String,
    override val type: Int
) : SongListItem()
