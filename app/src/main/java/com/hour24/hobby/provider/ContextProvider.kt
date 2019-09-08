package com.hour24.hobby.provider

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ContextProvider(private val context: AppCompatActivity) {

    fun getContext() = context

    fun getString(resId: Int) = context.getString(resId)

    fun getColor(resId: Int) = ContextCompat.getColor(context, resId)

    fun getSupportFragmentManager() = context.supportFragmentManager

}