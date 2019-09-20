package com.hour24.hobby.view.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hour24.hobby.R
import com.hour24.hobby.databinding.MainActivityBinding
import com.hour24.hobby.databinding.MainCourseItemBinding
import com.hour24.hobby.model.CourseItem
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.view.activity.BaseActivity
import com.hour24.hobby.view.recent.RecentViewModel
import com.hour24.hobby.viewmodel.CourseViewModel
import com.hour24.tb.adapter.GenericRecyclerViewAdapter
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_tabbar.*

class MainActivity : BaseActivity(), View.OnClickListener {

    private val mBinding: MainActivityBinding by lazy {
        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
    }

    private val mMainVM: MainViewModel = MainViewModel(ContextProvider(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_home -> {
                rv_main.smoothScrollToPosition(0)
            }
        }
    }

    override fun initLayout() {

        mBinding.run {

            // viewModel
            mainVM = mMainVM.apply {
                setFragmentManager(supportFragmentManager)
            }
        }

        val views = arrayOf(iv_home)
        views.forEach {
            it.setOnClickListener(this)
        }

        // adapter
        rv_main.adapter =
            object :
                GenericRecyclerViewAdapter<CourseItem, MainCourseItemBinding>(R.layout.main_course_item) {
                override fun onBindData(
                    position: Int,
                    model: CourseItem,
                    dataBinding: MainCourseItemBinding
                ) {

                    dataBinding.courseVM =
                        CourseViewModel(ContextProvider(this@MainActivity)).apply {
                            setModel(model)
                        }

                }
            }
    }

}
