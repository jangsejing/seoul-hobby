package com.hour24.hobby.provider

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.content.ContextCompat

class ContextProvider(private val mContext: Context) {

    fun getContext() = mContext

    fun getActivity() = mContext as Activity

    fun getString(resId: Int) = mContext.getString(resId).toString()

    fun getColor(resId: Int) = ContextCompat.getColor(mContext, resId)

    fun getBitmap(resId: Int) = BitmapFactory.decodeResource(mContext.resources, resId)


}