package com.hour24.hobby.viewmodel

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.network.RetrofitService
import com.hour24.hobby.provider.ContextProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class MainViewModel(val contextProvider: ContextProvider) {

    /**
     * 오프라인 강의 리스트
     */
    val offlineCousrceList: ObservableField<ArrayList<OfflineItemModel>> = ObservableField()

    fun getOfflineCourseList(
        startIndex: Int = 1,
        endIndex: Int = 100,
        search: String = " ",
        date: String = "201909"
    ) {
        RetrofitService.seoul
            .reqOfflineCourse(startIndex, endIndex, search, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Flowable.just(it.offlineCourse)
            }
            .flatMap {
                if (it.result.code != "INFO-000") {
                    Flowable.error(Exception("result code : ${it.result.code}"))
                } else {
                    Flowable.just(it.row)
                }
            }
            .subscribe({
                offlineCousrceList.set(it)
            }) {
                it.printStackTrace()
            }
    }
}