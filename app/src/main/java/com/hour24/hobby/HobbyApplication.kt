package com.hour24.hobby

import android.app.Application
import com.hour24.hobby.room.AppDatabase
import timber.log.Timber

class HobbyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        // database init
        AppDatabase.getInstance(applicationContext)
    }

    override fun onTerminate() {
        AppDatabase.destroyInstance()
        super.onTerminate()

    }
}
