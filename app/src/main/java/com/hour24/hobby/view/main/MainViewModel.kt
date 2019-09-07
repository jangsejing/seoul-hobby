package com.hour24.hobby.view.main

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hour24.hobby.const.APIConst
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.network.RetrofitService
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.DateUtils
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class MainViewModel(val contextProvider: ContextProvider) {

    init {
        getOfflineCourseList()
    }

    private val offlineCourseList: ObservableField<List<OfflineItemModel>> by lazy {
        ObservableField<List<OfflineItemModel>>()
    }

    /**
     * 오프라인 강좌
     */
    fun getOfflineCourseList(
        startIndex: Int = 1,
        endIndex: Int = 100,
        date: String = DateUtils.convertDateFormat(DateUtils.YYYYMM),
        search: String = " "
    ) {
        RetrofitService.seoul
            .reqOfflineCourse(startIndex, endIndex, search, date)
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

    fun getList() = offlineCourseList

}