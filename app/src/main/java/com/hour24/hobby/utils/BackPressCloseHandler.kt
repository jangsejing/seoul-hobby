package com.hour24.hobby.utils

import android.widget.Toast
import androidx.core.app.ComponentActivity
import com.hour24.hobby.R


class BackPressCloseHandler(private val activity: ComponentActivity) {

    private var backKeyPressedTime: Long = 0
    private var toast: Toast? = null

    fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            showGuide()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish()
            toast!!.cancel()
        }
    }

    fun showGuide() {
        toast = Toast.makeText(activity, R.string.app_back, Toast.LENGTH_SHORT)
        toast!!.show()
    }


}
