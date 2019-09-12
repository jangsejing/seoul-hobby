package com.hour24.hobby.view.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hour24.hobby.R
import com.hour24.hobby.databinding.DetailActivityBinding
import com.hour24.hobby.databinding.MainActivityBinding
import com.hour24.hobby.view.activity.BaseActivity

class DetailActivity : BaseActivity(), View.OnClickListener {

    private val mBinding: DetailActivityBinding by lazy {
        DataBindingUtil.setContentView<DetailActivityBinding>(this, R.layout.detail_activity)
    }

//    private val mViewModel: MainViewModel =
//        MainViewModel(ContextProvider(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    override fun initLayout() {

        mBinding.run {

        }

//        val views = arrayOf(iv_home)
//        views.forEach {
//            it.setOnClickListener(this)
//        }
    }

}
