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


class DetailActivity : BaseActivity(), View.OnClickListener {

    private val mBinding: DetailActivityBinding by lazy {
        DataBindingUtil.setContentView<DetailActivityBinding>(this, R.layout.detail_activity)
    }

    private val mViewModel: DetailViewModel = DetailViewModel(ContextProvider(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIntent()
        initLayout()
    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initIntent() {
        tryCatch {
            mViewModel.setModel(intent.getSerializableExtra(OfflineItemModel::class.java.name) as OfflineItemModel)
        }
    }

    override fun initLayout() {

        mBinding.run {
            setSupportActionBar(tbDetail)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }

            viewModel = mViewModel
        }

//        val views = arrayOf(iv_home)
//        views.forEach {
//            it.setOnClickListener(this)
//        }
    }


}
