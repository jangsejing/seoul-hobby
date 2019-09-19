package com.hour24.hobby.view.extentions

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import com.hour24.hobby.utils.tryCatch


fun Context.toast(
    text: String
) {
    tryCatch {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}

fun Context.toast(
    resId: Int
) {
    tryCatch {
        Toast.makeText(this, this.getString(resId), Toast.LENGTH_SHORT).show()
    }
}

fun Context.getDP(db: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        db.toFloat(),
        this.resources.displayMetrics
    ).toInt()
}