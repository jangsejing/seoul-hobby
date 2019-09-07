package com.hour24.hobby.extentions

import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hour24.hobby.utils.DateUtils
import com.hour24.hobby.utils.tryCatch
import com.hour24.tb.adapter.GenericRecyclerViewAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("addAllItem")
fun RecyclerView.addAllItem(
    list: List<Any>?
) {
    tryCatch {
        (this.adapter as? GenericRecyclerViewAdapter<Any, *>)?.run {
            this.addAllItem(list)
        }
    }
}

/**
 * 다른 형태의 포맷으로 날짜 타입 변경
 *
 * @param date
 * @param originalFormat
 * @param convertFormat
 */
@BindingAdapter("date", "originalFormat", "convertFormat")
fun TextView.convertDateFormat(
    date: String,
    originalFormat: String,
    convertFormat: String
) {
    tryCatch {
        this.text = DateUtils.convertDateFormat(date, originalFormat, convertFormat)
    }
}