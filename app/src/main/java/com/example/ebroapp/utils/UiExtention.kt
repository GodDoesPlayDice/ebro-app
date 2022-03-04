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
import androidx.fragment.app.FragmentTransaction
import com.example.ebroapp.R
import com.example.ebroapp.utils.CacheUtils.addBitmapToMemoryCache
import com.example.ebroapp.utils.CacheUtils.getBitmapFromMemCache
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun TextView.setTime(time: Int) {
    val minutes = time / 60
    val seconds = time % 60
    val stringMinutes = if (minutes >= 10) minutes else "0$minutes"
    val stringSeconds = if (seconds >= 10) seconds else "0$seconds"
    this.text = "$stringMinutes:$stringSeconds"
}

fun ContentResolver.setOnVolumeChangeListener(
    volumeObserver: VolumeObserver
) {
    this.registerContentObserver(CONTENT_URI, true, volumeObserver)
}

fun FragmentTransaction.setAnimation() =
    this.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)

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

fun ShapeableImageView.setImageFromUri(uri: Uri) {
    val view = this
    GlobalScope.launch(Dispatchers.Main) {
        val bitmap: Bitmap? = withContext(Dispatchers.IO) {
            var bitmap: Bitmap? = getBitmapFromMemCache(uri.toString())
            if (bitmap != null) {
                bitmap
            } else {
                val dataColumn = arrayOf(MediaStore.Audio.Media.DATA)
                context.contentResolver.query(uri, dataColumn, null, null, null)
                    ?.use { coverCursor ->
                        coverCursor.moveToFirst()
                        val dataIndex: Int = coverCursor.getColumnIndex(MediaStore.Audio.Media.DATA)
                        val filePath = coverCursor.getString(dataIndex)
                        val retriever = MediaMetadataRetriever()
                        retriever.setDataSource(filePath)
                        val coverBytes = retriever.embeddedPicture
                        coverBytes?.let {
                            bitmap = BitmapFactory.decodeByteArray(coverBytes, 0, coverBytes.size)
                        }
                    }
                addBitmapToMemoryCache(uri.toString(), bitmap)
                bitmap
            }
        }
        bitmap?.let { view.setImageBitmap(it) } ?: view.setImageResource(R.drawable.ic_logo)
    }
}

