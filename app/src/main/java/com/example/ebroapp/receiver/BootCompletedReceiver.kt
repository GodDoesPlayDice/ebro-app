package com.example.ebroapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startService(Intent(context, BootCompleteService::class.java))
    }
}