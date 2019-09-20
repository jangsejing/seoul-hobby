package com.hour24.hobby.view.recent

import android.annotation.SuppressLint
import androidx.databinding.ObservableBoolean
import com.hour24.hobby.room.AppDatabase
import com.hour24.hobby.room.recent.RecentEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import com.hour24.hobby.provider.ContextProvider


/**
 * 최근 본 강의
 */
@SuppressLint("CheckResult")
class RecentViewModel(private val mContextProvider: ContextProvider) {

    private val mIsBookmark = ObservableBoolean()
    private var mIsBinding = false

    init {
        mIsBookmark.set(false)
    }

    fun isBookmark(id: String): ObservableBoolean {
        selectCountById(id)
        return mIsBookmark
    }

//    /**
//     * 체크박스
//     */
//    fun onCheckedChanged(v: CompoundButton, isChecked: Boolean, id: String, data: String) {
//        when (v.id) {
//            R.id.cv_bookmark -> {
//                if (isChecked) insert(id, data) else delete(id)
//            }
//        }
//    }

    /**
     * Database
     */
    private fun getDatabase() = AppDatabase.getInstance(mContextProvider.getContext())!!

    private fun selectById(id: String) {
        getDatabase()
            .recentDao()
            .selectById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d(id)
            }, {
                Timber.e(it)
            })
    }

    private fun selectCountById(id: String) {

        if (mIsBinding) {
            return
        }

        Timber.d(id)

        getDatabase()
            .recentDao()
            .selectCountById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it > 0) mIsBookmark.set(true) else mIsBookmark.set(false)
                mIsBinding = true
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

}
