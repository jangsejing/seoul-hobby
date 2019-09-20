package com.hour24.hobby.view.recent

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.hour24.hobby.R
import com.hour24.hobby.databinding.CourseItemBinding
import com.hour24.hobby.databinding.RecentActivityBinding
import com.hour24.hobby.model.CourseItem
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.view.activity.BaseActivity
import com.hour24.hobby.viewmodel.CourseViewModel
import com.hour24.tb.adapter.GenericRecyclerViewAdapter
import kotlinx.android.synthetic.main.recent_activity.*

class RecentActivity : BaseActivity() {

    private val mBinding: RecentActivityBinding by lazy {
        DataBindingUtil.setContentView<RecentActivityBinding>(this, R.layout.recent_activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initLayout() {

        mBinding.run {
            setSupportActionBar(tb_recent)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }

            recentVM = RecentViewModel(ContextProvider(this@RecentActivity))
        }

        rv_recent.adapter =
            object :
                GenericRecyclerViewAdapter<CourseItem, CourseItemBinding>(R.layout.course_item) {
                override fun onBindData(
                    position: Int,
                    model: CourseItem,
                    dataBinding: CourseItemBinding
                ) {
                    dataBinding.courseVM =
                        CourseViewModel(ContextProvider(this@RecentActivity)).apply {
                            setModel(model)
                        }
                }
            }
    }
}
