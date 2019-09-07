package com.hour24.hobby.provider

import android.content.Context
import androidx.core.content.ContextCompat

class ContextProvider(private val context: Context) {

    fun getContext() = context

    fun getString(resId: Int) = context.getString(resId)

    fun getColor(resId: Int) = ContextCompat.getColor(context, resId)

}