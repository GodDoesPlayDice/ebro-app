package com.example.ebroapp.receiver

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder


class BootCompleteService : Service() {

    private val actionPowerReceiver by lazy { ActionPowerReceiver() }
    private val filter = IntentFilter().apply {
        addAction(Intent.ACTION_POWER_DISCONNECTED);
        addAction(Intent.ACTION_POWER_CONNECTED)
    }

    override fun onCreate() {
        super.onCreate()
        registerReceiver(actionPowerReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(actionPowerReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}