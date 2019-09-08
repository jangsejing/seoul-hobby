package com.hour24.hobby.view.main

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.ObservableField
import com.hour24.hobby.BuildConfig
import com.hour24.hobby.R
import com.hour24.hobby.consts.APIConst
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.network.RetrofitService
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.DateUtils
import com.hour24.hobby.view.search.SearchSheet
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

@SuppressLint("CheckResult")
class MainViewModel(val contextProvider: ContextProvider) {

    init {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        getOfflineCourseList()
    }

    private val offlineCourseList: ObservableField<List<OfflineItemModel>> by lazy {
        ObservableField<List<OfflineItemModel>>()
    }

    /**
     * 오프라인 강좌
     */
    fun getOfflineCourseList(
        startIndex: Int = APIConst.Default.startIndex,
        endIndex: Int = APIConst.Default.endIndex,
        date: String = DateUtils.convertDateFormat(DateUtils.YYYYMM), // 필수
        search: String = " "
    ) {

        val text = if (search.isEmpty()) " " else search

        RetrofitService.seoul
            .reqOfflineCourse(startIndex, endIndex, text, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Flowable.just(it.offlineCourse)
            }
            .flatMap {
                if (it.result.code != APIConst.Code.success) {
                    Flowable.error(Exception("result code : ${it.result.code}"))
                } else {
                    Flowable.just(it.row)
                }
            }
            .subscribe({
                offlineCourseList.set(it)
            }) {
                it.printStackTrace()
            }
    }

    // 리스트
    fun getList() = offlineCourseList

    fun onClick(v: View) {

        when (v.id) {

            R.id.iv_home -> {

            }

            // 검색
            R.id.iv_search -> {

                SearchSheet().run {

                    setOnSearchSheetListener(object : SearchSheet.OnSearchSheetListener {
                        override fun onDismiss(text: String) {
                            Timber.d(text)
                            val date = DateUtils.convertDateFormat(DateUtils.YYYYMM)
                            getOfflineCourseList(
                                APIConst.Default.startIndex,
                                APIConst.Default.endIndex,
                                date,
                                text
                            )
                        }
                    })

                    show(
                        contextProvider.getSupportFragmentManager(),
                        SearchSheet::class.java.name
                    )
                }
            }

            R.id.iv_bookmark -> {

            }

            R.id.iv_my_write -> {

            }
        }
    }

}