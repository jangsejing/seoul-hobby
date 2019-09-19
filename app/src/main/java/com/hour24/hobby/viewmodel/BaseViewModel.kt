package com.hour24.hobby.viewmodel

import androidx.fragment.app.FragmentManager
import com.hour24.hobby.provider.ContextProvider

open class BaseViewModel(private val mContextProvider: ContextProvider) {

    private var mFragmentManager: FragmentManager? = null

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.mFragmentManager = fragmentManager
    }

    fun getFragmentManager() = mFragmentManager
}