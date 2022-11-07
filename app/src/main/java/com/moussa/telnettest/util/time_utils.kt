package com.moussa.telnettest.util

import java.text.SimpleDateFormat
import java.util.*

fun Int.dateFormatter(): String {
    val format = "dd MMM yyyy HH:mm:ss"
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(Date(this.toLong() * 1000))
}