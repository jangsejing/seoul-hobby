package com.hour24.hobby.extentions

import android.content.Context
import android.widget.Toast
import com.hour24.hobby.utils.tryCatch


fun Context.showToast(
    text: String
) {
    tryCatch {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
