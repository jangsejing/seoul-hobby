package com.hour24.hobby.view.main

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.hour24.hobby.BuildConfig
import com.hour24.hobby.R
import com.hour24.hobby.consts.APIConst
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.network.RetrofitService
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.DateUtils
import com.hour24.hobby.view.search.SearchSheet
import com.hour24.hobby.viewmodel.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

@SuppressLint("CheckResult")
class MainViewModel(private val mContextProvider: ContextProvider) : BaseViewModel() {

    private var mIsClear: ObservableBoolean = ObservableBoolean()

    private val mOfflineCourseList: ObservableField<List<OfflineItemModel>> by lazy {
        ObservableField<List<OfflineItemModel>>()
    }

    init {
        getOfflineCourseList()
    }

    /**
     * 오프라인 강좌
     */
    fun getOfflineCourseList(
        isClear: Boolean = false,
        startIndex: Int = APIConst.Default.startIndex,
        endIndex: Int = APIConst.Default.endIndex,
        date: String = DateUtils.convertDateFormat(DateUtils.YYYYMM), // 필수
        search: String = " "
    ) {

        // 초기화 여부
        this.mIsClear.set(isClear)

        val text = if (search.isEmpty()) " " else search

        RetrofitService.seoul
            .reqOfflineCourse(startIndex, endIndex, text, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Flowable.just(it.offlineCourse)
            }
            .flatMap {
                Flowable.just(it.row)
//                if (it.result.code != APIConst.Code.success) {
//                    Flowable.error(Exception("result code : ${it.result.code}"))
//                } else {
//
//                }
            }
            .subscribe({
                mOfflineCourseList.set(it)
            }) {
                it.printStackTrace()
            }
    }

    // 리스트
    fun getList() = mOfflineCourseList

    // 리스트 초기화 여부
    fun isClear() = mIsClear

    fun onClick(v: View) {

        when (v.id) {

            // 검색
            R.id.iv_search -> {

                // 검색시트
                SearchSheet().run {

                    setOnSearchSheetListener(object : SearchSheet.OnSearchSheetListener {

                        override fun onDismiss(text: String, date: String) {
                            Timber.d(text)
                            getOfflineCourseList(
                                true,
                                APIConst.Default.startIndex,
                                APIConst.Default.endIndex,
                                date,
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

            R.id.iv_bookmark -> {

            }

            R.id.iv_my_write -> {

            }
        }
    }

}