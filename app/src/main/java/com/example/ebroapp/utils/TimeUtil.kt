package com.example.ebroapp.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    private val formatShort = SimpleDateFormat("HH:mm", Locale.getDefault())

    fun getShortTime(): String = formatShort.format(Date())
}