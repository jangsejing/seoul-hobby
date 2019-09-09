package com.hour24.hobby.provider

import android.content.Context
import androidx.core.content.ContextCompat

class ContextProvider(private val mContext: Context) {

    fun getContext() = mContext

    fun getString(resId: Int) = mContext.getString(resId)

    fun getColor(resId: Int) = ContextCompat.getColor(mContext, resId)


}