package com.hour24.hobby

import android.app.Application
import timber.log.Timber

class HobbyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}
