package com.example.ebroapp.utils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings.System.CONTENT_URI
import android.widget.SeekBar
import android.widget.TextView
import com.example.ebroapp.R
import com.example.ebroapp.utils.CacheUtil.addBitmapToMemoryCache
import com.example.ebroapp.utils.CacheUtil.getBitmapFromMemCache
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.GlobalScope

fun TextView.setTime(time: Int) {
    val minutes = time / 60
    val seconds = time % 60
    val stringMinutes = if (minutes >= 10) minutes else "0$minutes"
    val stringSeconds = if (seconds >= 10) seconds else "0$seconds"
    val text = "$stringMinutes:$stringSeconds"
    this.text = text
}

fun ContentResolver.setOnVolumeChangeListener(
    volumeObserver: VolumeObserver
) {
    this.registerContentObserver(CONTENT_URI, true, volumeObserver)
}

fun SeekBar.setOnSeekBarListener(addOp: (Int) -> Unit) {
    this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            addOp(15 + progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }
    )
}

fun ShapeableImageView.setImageFromUri(
    uri: Uri,
    placeholder: Int = R.drawable.ic_logo,
    error: Int = R.drawable.ic_logo
) {
    setImageResource(placeholder)
    GlobalScope.launchMain({
        val bitmap: Bitmap? = withIO {
            var bitmap: Bitmap? = getBitmapFromMemCache(uri.toString())
            if (bitmap != null) {
                bitmap
            } else {
                val dataColumn = arrayOf(MediaStore.Audio.Media.DATA)
                context.contentResolver.query(uri, dataColumn, null, null, null)
                    ?.use { coverCursor ->
                        coverCursor.moveToFirst()
                        val dataIndex = coverCursor.getColumnIndex(MediaStore.Audio.Media.DATA)
                        val filePath = coverCursor.getString(dataIndex)
                        val retriever = MediaMetadataRetriever()
                        retriever.setDataSource(filePath)
                        val coverBytes = retriever.embeddedPicture
                        coverBytes?.let {
                            bitmap = BitmapFactory.decodeByteArray(coverBytes, 0, it.size)
                        }
                    }
                addBitmapToMemoryCache(uri.toString(), bitmap)
                bitmap
            }
        }
        bitmap?.let { setImageBitmap(it) } ?: setImageResource(error)
    }, { setImageResource(error) })
}

