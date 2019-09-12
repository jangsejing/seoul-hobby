package com.hour24.hobby.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hour24.hobby.R
import com.hour24.hobby.databinding.SearchSheetBinding
import kotlinx.android.synthetic.main.search_sheet.*

class SearchSheet : BottomSheetDialogFragment(), View.OnClickListener {

    interface OnSearchSheetListener {
        fun onDismiss(text: String, date: String)
    }

    private lateinit var mBinding: SearchSheetBinding
    private lateinit var mListener: OnSearchSheetListener
    private val mViewModel = SearchViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = inflate(inflater, R.layout.search_sheet, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    fun setOnSearchSheetListener(listener: OnSearchSheetListener) {
        mListener = listener
    }

    private fun initLayout() {

        val views = arrayOf(tv_submit, iv_close)
        views.forEach {
            (it as View).setOnClickListener(this)
        }

        mBinding.run {
            viewModel = mViewModel
        }
    }

    override fun onClick(v: View) {

        when (v.id) {

            R.id.iv_close -> {
                dismiss()
            }

            // 검색
            R.id.tv_submit -> {

                // 강의명
                val text = et_search.text.toString()

                // 신청시작일
                val date = String.format("%d%02d", np_year.value, np_month.value)

                mListener.onDismiss(text, date)
                dismiss()
            }
        }
    }

}
