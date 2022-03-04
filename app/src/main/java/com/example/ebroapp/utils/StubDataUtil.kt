package com.example.ebroapp.utils

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore.Audio
import com.example.ebroapp.domain.DomainRepository
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SONG


const val selection = "${Audio.Media.IS_MUSIC}!=0"
val uri: Uri = Audio.Media.EXTERNAL_CONTENT_URI

val projection = arrayOf(
    Audio.Media._ID,
    Audio.Media.TITLE,
    Audio.Media.ALBUM,
    Audio.Media.ARTIST
)

suspend fun getMusicList(context: Context): List<Song> {
    val songs: MutableList<Song> = mutableListOf()
    context.contentResolver.query(
        uri, projection, selection, null, null
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
            val isFavorite = DomainRepository.obtain().isFavoriteSong(id)
            songs.add(Song(id, uri, title, album, artist, TYPE_SONG, isFavorite))
        }
    }
    return songs
}