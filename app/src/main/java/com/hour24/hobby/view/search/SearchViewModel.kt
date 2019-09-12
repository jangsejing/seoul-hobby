package com.hour24.hobby.view.search

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import com.hour24.hobby.utils.DateUtils

@SuppressLint("CheckResult")
class SearchViewModel {

    private val mYearList = ObservableField<List<Int>>()
    private val mMonthList = ObservableField<List<Int>>()

    init {
        setYearLst()
        setMonthList()
    }

    fun getYearList() = mYearList

    fun getMonthList() = mMonthList

    /**
     * 현재년도 구하기 (+5년)
     */
    private fun setYearLst() {
        val year = DateUtils.convertDateFormat(DateUtils.YYYY).toInt()
        val yearList = ArrayList<Int>()
        for (i in 0..5) {
            yearList.add(year + i)
        }
        mYearList.set(yearList)
    }

    /**
     * 일 구하기
     */
    private fun setMonthList() {
        val monthList = ArrayList<Int>()
        for (i in 1..12) {
            monthList.add(i)
        }
        mMonthList.set(monthList)
    }

}