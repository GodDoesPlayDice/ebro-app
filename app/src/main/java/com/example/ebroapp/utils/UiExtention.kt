package com.example.ebroapp.utils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.provider.Settings.System.CONTENT_URI
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.ebroapp.R
import com.google.android.material.imageview.ShapeableImageView

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

fun ShapeableImageView.setImageBitmapOrPlaceholder(bitmap: Bitmap?) {
    bitmap?.let { this.setImageBitmap(it) } ?: this.setImageResource(R.drawable.ic_logo)
}