package com.example.ebroapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_POWER_CONNECTED
import android.content.Intent.ACTION_POWER_DISCONNECTED
import android.window.SplashScreen
import com.example.ebroapp.view.activity.black.BlackActivity
import com.example.ebroapp.view.activity.splash.SplashActivity

class ActionPowerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        when (intent?.action) {
            ACTION_POWER_CONNECTED -> {
                context.startActivity(Intent(context, SplashActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
            ACTION_POWER_DISCONNECTED -> {
                context.startActivity(Intent(context, BlackActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        }
    }
}