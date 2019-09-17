package com.hour24.hobby

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.hour24.hobby.room.AppDatabase
import timber.log.Timber

class HobbyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initRoom()
        initFirebase()
    }

    override fun onTerminate() {
        AppDatabase.destroyInstance()
        super.onTerminate()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initRoom() {
        // database init
        AppDatabase.getInstance(applicationContext)
    }

    private fun initFirebase() {
        FirebaseAuth.getInstance().addAuthStateListener {

        }
    }
}
