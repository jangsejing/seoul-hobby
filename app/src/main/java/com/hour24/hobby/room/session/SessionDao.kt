package com.hour24.hobby.room.session

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Observable
import kr.goodchoice.abouthere.database.dao.BaseDao

@Dao
interface SessionDao : BaseDao<SessionEntity> {

    @Query("SELECT * FROM session")
    fun select(): Observable<SessionEntity>

    @Query("SELECT count(*) FROM session")
    fun selectCount(): Observable<Int>

}