package com.hour24.hobby.network

import retrofit2.Call
import retrofit2.Callback

abstract class CallbackWithRetry<T>(private val call: Call<T>) : Callback<T> {

    private var retryCount = 0
    private val retryMax = 3

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (retryCount++ < retryMax) {
            retry()
        }
    }

    private fun retry() {
        call.clone().enqueue(this)
    }
}
