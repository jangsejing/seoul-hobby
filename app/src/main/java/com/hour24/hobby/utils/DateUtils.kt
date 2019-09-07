package com.hour24.hobby.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val YYYYMM = "yyyyMM"

    /**
     * 다른 형태의 포맷으로 날짜 타입 변경
     */
    fun convertDateFormat(format: String): String {
        tryCatch {
            return SimpleDateFormat(format, Locale.KOREA).format(System.currentTimeMillis())
        }
        return ""
    }

    /**
     * 다른 형태의 포맷으로 날짜 타입 변경
     */
    fun convertDateFormat(time: Long, format: String): String {
        tryCatch {
            return SimpleDateFormat(format, Locale.KOREA).format(time)
        }
        return ""
    }
}