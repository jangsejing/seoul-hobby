package com.hour24.hobby.view.main

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.hour24.hobby.R
import com.hour24.hobby.consts.APIConst
import com.hour24.hobby.consts.CourseConst
import com.hour24.hobby.model.CourseItem
import com.hour24.hobby.network.RetrofitService
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.DateUtils
import com.hour24.hobby.view.search.SearchSheet
import com.hour24.hobby.viewmodel.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import com.hour24.hobby.view.extentions.toast

@SuppressLint("CheckResult")
class MainViewModel(private val mContextProvider: ContextProvider) :
    BaseViewModel(mContextProvider) {

    private val mCourseList = ObservableField<List<CourseItem>>()
    private var mIsClear: ObservableBoolean = ObservableBoolean()
    private var mText = ObservableField<String>()
    private var mDate = ObservableField<String>() // 노출용
    private var mYear = ObservableInt()
    private var mMonth = ObservableInt()

    init {
        mText.set("")
        mYear.set(DateUtils.convertDateFormat(DateUtils.MM).toInt())
        mMonth.set(DateUtils.convertDateFormat(DateUtils.YYYY).toInt())
        getOfflineCourseList()
    }

    /**
     * 오프라인 강좌
     */
    fun getOfflineCourseList(
        isClear: Boolean = false,
        startIndex: Int = APIConst.Default.startIndex,
        endIndex: Int = APIConst.Default.endIndex,
        year: Int = DateUtils.convertDateFormat(DateUtils.YYYY).toInt(),
        month: Int = DateUtils.convertDateFormat(DateUtils.MM).toInt(),
        search: String = " "
    ) {

        val text = if (search.isEmpty()) " " else search
        val date = String.format("%d%02d", year, month)

        Timber.d("$text / $date")

        RetrofitService.seoul
            .reqOfflineCourse(startIndex, endIndex, text, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                if (it.course != null) {
                    Flowable.just(it.course.row)
                } else {
                    mContextProvider.getContext().toast(R.string.search_empty)
                    Flowable.error {
                        Throwable()
                    }
                }
            }
            .subscribe({
                // 초기화 여부
                this.mIsClear.set(isClear)

                mCourseList.set(it)
                mText.set(text)
                mYear.set(year)
                mMonth.set(month)

                // 노출용
                mDate.set(String.format("%d%02d", year, month))

            }) {
                it.printStackTrace()
            }
    }

    fun getSearchTag(type: CourseConst.SearchTag): ObservableField<String> {
        return if (type == CourseConst.SearchTag.TEXT) {
            mText
        } else {
            mDate
        }
    }

    fun onClick(v: View) {

        when (v.id) {

            // 검색
            R.id.iv_search -> {
                SearchSheet().run {

                    setOnSearchSheetListener(this@MainViewModel,
                        object : SearchSheet.OnSearchSheetListener {

                            override fun onDismiss(text: String, year: Int, month: Int) {
                                getOfflineCourseList(
                                    true,
                                    APIConst.Default.startIndex,
                                    APIConst.Default.endIndex,
                                    year,
                                    month,
                                    text
                                )
                            }
                        })

                    super.getFragmentManager()?.let {
                        show(
                            it,
                            SearchSheet::class.java.name
                        )
                    }
                }
            }

            // 최근 본 강의
            R.id.iv_recent -> {

            }

        }
    }

    // 리스트
    fun getList() = mCourseList

    // 리스트
    fun getText() = mText

    // 리스트
    fun getYear() = mYear

    // 리스트
    fun getMonth() = mMonth

    // 리스트 초기화 여부
    fun isClear() = mIsClear
}