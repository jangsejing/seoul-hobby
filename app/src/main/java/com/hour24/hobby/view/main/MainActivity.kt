package com.hour24.hobby.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hour24.hobby.R
import com.hour24.hobby.consts.APIConst
import com.hour24.hobby.databinding.CourseItemBinding
import com.hour24.hobby.databinding.MainActivityBinding
import com.hour24.hobby.model.CourseItem
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.BackPressCloseHandler
import com.hour24.hobby.view.activity.BaseActivity
import com.hour24.hobby.view.recent.RecentActivity
import com.hour24.hobby.view.search.SearchSheet
import com.hour24.hobby.viewmodel.CourseViewModel
import com.hour24.tb.adapter.GenericRecyclerViewAdapter
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_tabbar.*

class MainActivity : BaseActivity(), View.OnClickListener {

    private val mBinding: MainActivityBinding by lazy {
        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
    }

    private val mMainVM: MainViewModel = MainViewModel(ContextProvider(this))

    private val mBackHandler = BackPressCloseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    override fun onBackPressed() {
        mBackHandler.onBackPressed()
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_home -> {
                rv_main.smoothScrollToPosition(0)
            }

            // 검색
            R.id.iv_search -> {
                SearchSheet().apply {
                    setOnSearchSheetListener(mBinding.mainVM!!,
                        object : SearchSheet.OnSearchSheetListener {

                            override fun onDismiss(text: String, year: Int, month: Int) {
                                mBinding.mainVM?.getOfflineCourseList(
                                    true,
                                    APIConst.Default.startIndex,
                                    APIConst.Default.endIndex,
                                    year,
                                    month,
                                    text
                                )
                            }
                        })

                    show(
                        supportFragmentManager, SearchSheet::class.java.name
                    )
                }
            }

            // 최근 본 강의
            R.id.iv_recent -> {
                startActivity(Intent(this, RecentActivity::class.java))
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

        val views = arrayOf(iv_home, iv_search, iv_recent)
        views.forEach {
            it.setOnClickListener(this)
        }

        // adapter
        rv_main.adapter =
            object :
                GenericRecyclerViewAdapter<CourseItem, CourseItemBinding>(R.layout.course_item) {
                override fun onBindData(
                    position: Int,
                    model: CourseItem,
                    dataBinding: CourseItemBinding
                ) {

                    dataBinding.courseVM =
                        CourseViewModel(ContextProvider(this@MainActivity)).apply {
                            setModel(model)
                        }

                }
            }
    }

}
