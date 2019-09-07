package com.hour24.hobby.view.activity

import android.os.Bundle
import com.hour24.hobby.R
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    val vm = MainViewModel(ContextProvider(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun initViewModel() {
//        ViewModelProviders.of(this).get(MainViewModel::class.java).apply {
//            getOfflineCourseList()
//        }

        vm.getOfflineCourseList()

    }

}
