package com.hour24.hobby.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val YYYY = "yyyy"
    const val YYYYMM = "yyyyMM"
    const val YYYYMMDDHHMM = "yyyyMMddHHmm"
    const val YYYYMMDD = "yyyyMMdd"
    const val YYYY_MM_DD = "yyyy.MM.dd"

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

    /**
     * 다른 형태의 포맷으로 날짜 타입 변경
     */
    fun convertDateFormat(
        date: String,
        originalFormat: String,
        convertFormat: String
    ): String {
        tryCatch {

            if (date.isEmpty()) {
                return date
            }

            var format = originalFormat

            // 데이트와 오리지날 포맷이 맞지 않을때 (예외처리)
            if (!isValidDateFormat(date, originalFormat)) {
                format = YYYYMMDDHHMM
            }

            var sdf = SimpleDateFormat(format, Locale.KOREA)
            val d = sdf.parse(date)

            sdf = SimpleDateFormat(convertFormat, Locale.KOREA)
            return sdf.format(d)
        }

        return date
    }

    /**
     * 날짜포맷 체크
     */
    fun isValidDateFormat(date: String, format: String): Boolean {
        return try {
            val dateFormat = SimpleDateFormat(format)
            dateFormat.isLenient = false
            dateFormat.parse(date)
            true
        } catch (e: Exception) {
            false
        }
    }

}