package com.example.ebroapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.ebroapp.di.Injector
import com.example.ebroapp.utils.music.Player
import com.example.ebroapp.view.activity.black.BlackActivity
import com.example.ebroapp.view.activity.splash.SplashActivity
import javax.inject.Inject

class ActionPowerReceiver : BroadcastReceiver() {

    @Inject
    lateinit var player: Player

    override fun onReceive(context: Context, intent: Intent?) {
        Injector.injectActionPowerReceiver(this)

        when (intent?.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                startActivity(context, SplashActivity::class.java)
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                player.stopMusic()
                startActivity(context, BlackActivity::class.java)
            }
        }
    }
}

private fun startActivity(context: Context, clazz: Class<*>) {
    context.startActivity(Intent(context, clazz).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
}