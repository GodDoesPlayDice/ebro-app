package com.example.ebroapp.utils.music

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore.Audio
import com.example.domain.entity.Song
import com.example.domain.entity.SongListItem.Companion.TYPE_SONG
import com.example.domain.repository.DomainRepository

const val SELECTION = "${Audio.Media.IS_MUSIC}!=0"
val uri: Uri = Audio.Media.EXTERNAL_CONTENT_URI

val projection = arrayOf(
    Audio.Media._ID,
    Audio.Media.TITLE,
    Audio.Media.ALBUM,
    Audio.Media.ARTIST
)

fun getMusicList(context: Context, domainRepository: DomainRepository): List<Song> {
    val songs: MutableList<Song> = mutableListOf()
    context.contentResolver.query(
        uri,
        projection,
        SELECTION,
        null,
        null
    )?.use { cursor ->
        val idColumn = cursor.getColumnIndexOrThrow(Audio.Media._ID)
        val titleColumn = cursor.getColumnIndexOrThrow(Audio.Media.TITLE)
        val albumColumn = cursor.getColumnIndexOrThrow(Audio.Media.ALBUM)
        val artistColumn = cursor.getColumnIndexOrThrow(Audio.Media.ARTIST)

        while (cursor.moveToNext()) {
            val id = cursor.getLong(idColumn)
            val title = cursor.getString(titleColumn)
            val album = cursor.getString(albumColumn)
            val artist = cursor.getString(artistColumn)
            val uri: Uri = ContentUris.withAppendedId(uri, id)
            val isFavorite = domainRepository.isFavoriteSong(id)
            songs.add(Song(id, uri, title, album, artist, TYPE_SONG, isFavorite))
        }
    }
    return songs
}
