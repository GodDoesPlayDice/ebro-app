package com.example.ebroapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtil {

    const val MILLS_IN_SECOND = 1000
    const val SIXTY_UNITS = 60
    const val TEN_UNITS = 10
    const val DAY_1 = 1
    const val DAY_2 = 2
    const val DAY_3 = 3
    const val DAY_4 = 4

    private val formatLongDay = SimpleDateFormat("EEEE", Locale.US)

    fun getLongDay(time: Long): String = formatLongDay.format(Date(time * MILLS_IN_SECOND))
}
