package com.hour24.hobby.view.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hour24.hobby.R
import com.hour24.hobby.databinding.DetailActivityBinding
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.tryCatch
import com.hour24.hobby.view.activity.BaseActivity
import com.hour24.hobby.viewmodel.CourseViewModel
import kotlinx.android.synthetic.main.detail_input.*


class DetailActivity : BaseActivity(), View.OnClickListener {

    private val mBinding: DetailActivityBinding by lazy {
        DataBindingUtil.setContentView<DetailActivityBinding>(this, R.layout.detail_activity)
    }

    private val mDetailVM = DetailViewModel(ContextProvider(this))
    private val mCourseVM = CourseViewModel(ContextProvider(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIntent()
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

    private fun initIntent() {
        tryCatch {
            mCourseVM.setModel(intent.getSerializableExtra(OfflineItemModel::class.java.name) as OfflineItemModel)
        }
    }

    override fun initLayout() {

        mBinding.run {
            setSupportActionBar(tbDetail)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }

            courseVM = mCourseVM
        }

        val views = arrayOf(bt_submit)
        views.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_submit -> {
                mDetailVM.onSubmit(mCourseVM.getModel().id, et_search.text.toString())
            }
        }
    }

}
