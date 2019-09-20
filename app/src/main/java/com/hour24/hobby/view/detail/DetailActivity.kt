package com.hour24.hobby.view.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hour24.hobby.R
import com.hour24.hobby.databinding.DetailActivityBinding
import com.hour24.hobby.databinding.DetailCommentItemBinding
import com.hour24.hobby.model.CommentItem
import com.hour24.hobby.model.CourseItem
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.tryCatch
import com.hour24.hobby.view.activity.BaseActivity
import com.hour24.hobby.view.detail.viewmodel.CommentViewModel
import com.hour24.hobby.view.detail.viewmodel.DetailViewModel
import com.hour24.hobby.viewmodel.CourseViewModel
import com.hour24.tb.adapter.GenericRecyclerViewAdapter
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.detail_input.*


class DetailActivity : BaseActivity(), View.OnClickListener {

    private val mBinding: DetailActivityBinding by lazy {
        DataBindingUtil.setContentView<DetailActivityBinding>(this, R.layout.detail_activity)
    }

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
            // 강좌정보
            mCourseVM.setModel(intent.getSerializableExtra(CourseItem::class.java.name) as CourseItem)
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
            detailVM = DetailViewModel(
                ContextProvider(this@DetailActivity),
                mCourseVM.getModel().id
            )
        }

        val views = arrayOf(bt_submit)
        views.forEach {
            it.setOnClickListener(this)
        }

        rv_detail.adapter =
            object :
                GenericRecyclerViewAdapter<CommentItem, DetailCommentItemBinding>(R.layout.detail_comment_item) {
                override fun onBindData(
                    position: Int,
                    model: CommentItem,
                    dataBinding: DetailCommentItemBinding
                ) {
                    dataBinding.apply {
                        commentVM =
                            CommentViewModel(ContextProvider(this@DetailActivity)).apply {
                                setModel(model)
                            }

                        detailVM = mBinding.detailVM
                    }
                }
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_submit -> {
                mBinding.detailVM?.onSubmit()
            }
        }
    }

}
