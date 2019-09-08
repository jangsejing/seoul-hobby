package com.hour24.hobby.view.search

import android.annotation.SuppressLint
import android.view.View
import android.widget.EditText
import androidx.databinding.ObservableField
import com.hour24.hobby.R

@SuppressLint("CheckResult")
class SearchViewModel {

    val text: ObservableField<String> = ObservableField()

    fun onClick(v: View) {

        when (v.id) {

            R.id.iv_close -> {
                text.set(text.toString())
            }

            // 검색
            R.id.tv_submit -> {
                text.set("자바")
            }
        }
    }

}