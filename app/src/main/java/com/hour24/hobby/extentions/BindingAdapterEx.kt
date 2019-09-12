package com.hour24.hobby.extentions

import android.os.Build
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hour24.hobby.utils.DateUtils
import com.hour24.hobby.utils.tryCatch
import com.hour24.tb.adapter.GenericRecyclerViewAdapter

@BindingAdapter("addAllItem")
fun RecyclerView.addAllItem(
    list: List<Any>?
) {
    addAllItem(list, false)
}

@BindingAdapter("addAllItem", "isClear")
fun RecyclerView.addAllItem(
    list: List<Any>?,
    isClear: Boolean
) {
    tryCatch {
        (this.adapter as? GenericRecyclerViewAdapter<Any, *>)?.run {

            if (isClear) {
                this.clear()
            }

            this.addAllItem(list)
        }
    }
}

@BindingAdapter("addAllItem", "default")
fun NumberPicker.addAllItem(
    list: List<Int>?,
    default: Int
) {
    tryCatch {
        list?.let {
            this.minValue = it[0]
            this.maxValue = it[it.size - 1]
        }
        this.value = default
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

/**
 * html format
 *
 * @param html
 */
@BindingAdapter("htmlFormat")
fun TextView.setHtmlFormat(html: String) {
    tryCatch {
        this.text = HtmlCompat.fromHtml(html, 0)
        return
    }

    this.text = html
}
