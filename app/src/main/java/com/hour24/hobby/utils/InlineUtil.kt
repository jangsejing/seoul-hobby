package com.hour24.hobby.utils

inline fun tryCatch(action: () -> Unit) {
    try {
        action()
    } catch (e: Exception) {

    }
}