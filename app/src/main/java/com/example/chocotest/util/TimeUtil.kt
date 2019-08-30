package com.example.chocotest.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

enum class TimeUtil {
    INSTANCE;

    companion object {
        const val SIMPLE_DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val SIMPLE_DATE_FORMAT_YY_MM_DD = "yyyy-MM-dd"
    }

    @SuppressLint("SimpleDateFormat")
    fun transferToNewFormat(timeString: String, newPattern: String): String {
        val format = SimpleDateFormat(
            SIMPLE_DATE_FORMAT_PATTERN, Locale.TAIWAN
        )
        val date: Date = format.parse(timeString)
        return SimpleDateFormat(newPattern).format(date).toString()
    }

}