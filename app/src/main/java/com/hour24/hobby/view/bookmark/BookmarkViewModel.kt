package com.hour24.hobby.view.bookmark

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.room.AppDatabase
import com.hour24.hobby.room.BookmarkEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

@SuppressLint("CheckResult")
class BookmarkViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Database
     */
    private fun getDatabase() = AppDatabase.getInstance(getApplication())!!

    fun selectById(model: OfflineItemModel) {
        getDatabase()
            .bookmarkDao()
            .selectById(model.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("bookmark : $it")
            }, {
                Timber.e(it)
            })
    }

    fun insert(model: OfflineItemModel) {

        val json = Gson().toJson(model)
        Timber.d("bookmark $json")

        getDatabase()
            .bookmarkDao()
            .insert(BookmarkEntity(model.id, json))
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.d("bookmark insert")
            }, {
                Timber.e(it)
            })
    }

}
