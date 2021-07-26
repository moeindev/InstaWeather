package ir.moeindeveloper.instaweather.feature.common.util

import java.text.SimpleDateFormat
import java.util.*

fun Int.toHour(): String {
    return try {
        val dtf = SimpleDateFormat("hh a")
        val date = Date((this * 1000).toLong())
        dtf.format(date)
    } catch (ex: Exception) {
        ex.printStackTrace()
        ""
    }
}