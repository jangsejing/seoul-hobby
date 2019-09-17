package com.hour24.hobby.utils

import timber.log.Timber

inline fun tryCatch(action: () -> Unit) {
    try {
        action()
    } catch (e: Exception) {
        Timber.e(e)
    }
}