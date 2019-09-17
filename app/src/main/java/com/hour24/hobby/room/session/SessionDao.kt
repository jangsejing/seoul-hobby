package com.hour24.hobby.room.session

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hour24.hobby.room.bookmark.BookmarkEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import kr.goodchoice.abouthere.database.dao.BaseDao

@Dao
interface SessionDao : BaseDao<SessionEntity> {

    @Query("SELECT * FROM session")
    fun select(): Observable<SessionEntity>

    @Query("SELECT count(*) FROM session")
    fun selectCount(): Observable<Int>

}