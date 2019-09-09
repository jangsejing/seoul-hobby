package com.hour24.hobby.viewmodel

import androidx.fragment.app.FragmentManager

open class BaseViewModel {

    private var mFragmentManager: FragmentManager? = null

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.mFragmentManager = fragmentManager
    }

    fun getFragmentManager() = mFragmentManager
}