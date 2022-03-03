package com.example.ebroapp.utils

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import android.provider.MediaStore.Audio
import com.example.ebroapp.domain.DomainRepository
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SONG


const val selection = "${MediaStore.Audio.Media.IS_MUSIC}!=0"
val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

val projection = arrayOf(
    Audio.Media._ID,
    Audio.Media.TITLE,
    Audio.Media.ALBUM,
    Audio.Media.ARTIST
)

fun getMusicList(context: Context): List<Song> {
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
            val dataColumn = arrayOf(Audio.Media.DATA)
            val isFavorite = DomainRepository.obtain().isFavoriteSong(id)
            var bitmap: Bitmap? = null
            context.contentResolver.query(uri, dataColumn, null, null, null)
                ?.use { coverCursor ->
                    coverCursor.moveToFirst()
                    val dataIndex: Int = coverCursor.getColumnIndex(Audio.Media.DATA)
                    val filePath = coverCursor.getString(dataIndex)
                    val retriever = MediaMetadataRetriever()
                    retriever.setDataSource(filePath)
                    val coverBytes = retriever.embeddedPicture
                    coverBytes?.let {
                        bitmap = BitmapFactory.decodeByteArray(coverBytes, 0, coverBytes.size)
                    }
                }
            songs.add(Song(id, bitmap, uri, title, album, artist, TYPE_SONG, isFavorite))
        }
    }
    return songs
}