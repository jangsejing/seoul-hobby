package com.hour24.hobby.view.search

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.hour24.hobby.utils.DateUtils

@SuppressLint("CheckResult")
class SearchViewModel {

    private val mYearList = ObservableField<List<Int>>()
    private val mMonthList = ObservableField<List<Int>>()

    private val mText = ObservableField<String>()
    private val mYear = ObservableInt()
    private val mMonth = ObservableInt()

    init {
        setYearLst()
        setMonthList()
    }

    fun getYearList() = mYearList

    fun getMonthList() = mMonthList

    fun getText() = mText

    fun getYear() = mYear

    fun getMonth() = mMonth

    fun setText(text: String) {
        mText.set(text)
    }

    fun setYear(year: Int) {
        mYear.set(year)
    }

    fun setMonth(month: Int) {
        mMonth.set(month)
    }

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