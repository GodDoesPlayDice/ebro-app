package com.example.ebroapp.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    private val formatLongDay = SimpleDateFormat("EEEE", Locale.US)

    fun getLongDay(time: Long): String = formatLongDay.format(Date(time * 1000))
}