package com.example.ebroapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.ebroapp.view.activity.MainActivity




class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val myIntent = Intent(context, MainActivity::class.java)
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(myIntent)
    }
}