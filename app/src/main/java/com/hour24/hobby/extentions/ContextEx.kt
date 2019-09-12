package com.hour24.hobby.extentions

import android.content.Context
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
