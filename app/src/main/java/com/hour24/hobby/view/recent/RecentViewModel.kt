package com.hour24.hobby.view.recent

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import com.google.gson.Gson
import com.hour24.hobby.model.CourseItem
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.room.AppDatabase
import com.hour24.hobby.room.recent.RecentEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


/**
 * 최근 본 강의
 */
@SuppressLint("CheckResult")
class RecentViewModel(private val mContextProvider: ContextProvider) {

    private val mCourseList = ObservableField<List<CourseItem>>()

    init {
        select()
    }

    /**
     * Database
     */
    private fun getDatabase() = AppDatabase.getInstance(mContextProvider.getContext())!!

    private fun select() {
        getDatabase()
            .recentDao()
            .selectAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                getCourseItem(it)
            }
            .subscribe({
                mCourseList.set(it)
            }, {
                Timber.e(it)
            })
    }

    fun insert(id: String, data: String) {
        getDatabase()
            .recentDao()
            .insert(RecentEntity(id, data))
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.d(id)
            }, {
                Timber.e(it)
            })
    }

    private fun delete(id: String) {
        getDatabase()
            .recentDao()
            .deleteById(id)
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.d(id)
            }, {
                Timber.e(it)
            })
    }

    @SuppressLint("CheckResult")
    private fun getCourseItem(list: List<RecentEntity>): Flowable<List<CourseItem>> {
        return Observable
            .fromIterable(list)
            .map {
                Gson().fromJson(it.data, CourseItem::class.java)
            }
            .toList()
            .toFlowable()
    }

    // 리스트
    fun getList() = mCourseList

}
