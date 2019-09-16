package com.hour24.hobby.view.bookmark

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.room.AppDatabase
import com.hour24.hobby.room.BookmarkEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import android.widget.CompoundButton
import com.hour24.hobby.R
import com.hour24.hobby.provider.ContextProvider
import kotlinx.android.synthetic.main.main_course_item_social.view.*


@SuppressLint("CheckResult")
class BookmarkViewModel(private val mContextProvider: ContextProvider) {

    private val mIsBookmark = ObservableBoolean()
    private var mIsBinding = false

    init {
        mIsBookmark.set(false)
    }

    fun isBookmark(id: String): ObservableBoolean {
        selectCountById(id)
        return mIsBookmark
    }

    /**
     * 체크박스
     */
    fun onCheckedChanged(v: CompoundButton, isChecked: Boolean, id: String, data: String) {
        when (v.id) {
            R.id.cv_bookmark -> {
                if (isChecked) insert(id, data) else delete(id)
            }
        }
    }

    /**
     * Database
     */
    private fun getDatabase() = AppDatabase.getInstance(mContextProvider.getContext())!!

    private fun selectById(id: String) {
        getDatabase()
            .bookmarkDao()
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
            .bookmarkDao()
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


    private fun insert(id: String, data: String) {
        getDatabase()
            .bookmarkDao()
            .insert(BookmarkEntity(id, data))
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.d(id)
            }, {
                Timber.e(it)
            })
    }

    private fun delete(id: String) {
        getDatabase()
            .bookmarkDao()
            .deleteById(id)
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.d(id)
            }, {
                Timber.e(it)
            })
    }

}
