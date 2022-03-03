package com.example.ebroapp.utils

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Size
import com.example.ebroapp.domain.DomainRepository
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SONG

const val selection = "${MediaStore.Audio.Media.IS_MUSIC}!=0"
val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

val projection = arrayOf(
    MediaStore.Audio.Media._ID,
    MediaStore.Audio.Media.TITLE,
    MediaStore.Audio.Media.ALBUM,
    MediaStore.Audio.Media.ARTIST
)

fun getMusicList(context: Context): List<Song> {
    val songs: MutableList<Song> = mutableListOf()
    context.contentResolver.query(
        uri, projection, selection, null, null
    )?.use {
        val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
        val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
        val albumColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
        val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)

        while (it.moveToNext()) {
            val id = it.getLong(idColumn)
            val title = it.getString(titleColumn)
            val album = it.getString(albumColumn)
            val artist = it.getString(artistColumn)
            val contentUri: Uri = ContentUris.withAppendedId(uri, id)
            val bitmap = context.contentResolver.loadThumbnail(contentUri, Size(300, 300), null)
            val isFavorite = DomainRepository.obtain().isFavoriteSong(id)
            songs.add(Song(id, bitmap, contentUri, title, album, artist, TYPE_SONG, isFavorite))
        }
    }
    return songs
}