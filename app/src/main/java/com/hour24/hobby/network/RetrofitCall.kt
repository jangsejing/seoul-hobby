package com.hour24.hobby.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RetrofitCall {

    fun <T> enqueueWithRetry(call: Call<T>, callback: Callback<T>) {
        call.enqueue(object : CallbackWithRetry<T>(call) {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(call, response)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                super.onFailure(call, t)
                callback.onFailure(call, t)
            }
        })
    }
}
