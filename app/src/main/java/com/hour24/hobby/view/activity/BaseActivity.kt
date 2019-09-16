package com.hour24.hobby.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hour24.hobby.BuildConfig
import com.hour24.hobby.R
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initLayout()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}
