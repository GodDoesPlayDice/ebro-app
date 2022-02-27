package com.example.ebroapp.utils

import android.content.Context
import android.database.ContentObserver
import android.media.AudioManager
import android.os.Handler


class VolumeObserver(context: Context, handler: Handler?, private val onChange: (Int) -> Unit) :
    ContentObserver(handler) {
    private var audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    override fun deliverSelfNotifications(): Boolean = false

    override fun onChange(selfChange: Boolean) {
        onChange.invoke(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) * 100 / 15)
    }
}