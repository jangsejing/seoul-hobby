package com.hour24.hobby.view.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hour24.hobby.R
import com.hour24.hobby.databinding.MainActivityBinding
import com.hour24.hobby.databinding.MainCourseItemBinding
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.view.activity.BaseActivity
import com.hour24.hobby.viewmodel.CourseItemViewModel
import com.hour24.tb.adapter.GenericRecyclerViewAdapter

class MainActivity : BaseActivity() {

    private val mBinding: MainActivityBinding by lazy {
        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
    }

    private val mViewModel: MainViewModel =
        MainViewModel(ContextProvider(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        initViewModel()
    }

    override fun initLayout() {

        mBinding.run {

            // viewModel
            viewModel = mViewModel

            // adapter
            rvMain.adapter =
                object :
                    GenericRecyclerViewAdapter<OfflineItemModel, MainCourseItemBinding>(R.layout.main_course_item) {
                    override fun onBindData(
                        position: Int,
                        model: OfflineItemModel,
                        dataBinding: MainCourseItemBinding
                    ) {
                        val viewModel =
                            CourseItemViewModel(ContextProvider(this@MainActivity))
                        viewModel.setModel(model)
                        dataBinding.viewModel = viewModel
                    }
                }
        }
    }

    override fun initViewModel() {

    }

}
