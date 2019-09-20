package com.hour24.hobby.room.recent

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface RecentDao {

    @Query("SELECT * FROM recent")
    fun selectAll(): Flowable<List<RecentEntity>>

    @Query("SELECT * FROM recent where id = :id")
    fun selectById(id: String): Observable<RecentEntity>


    @Query("SELECT count(*) FROM recent where id = :id")
    fun selectCountById(id: String): Observable<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: RecentEntity): Completable

    @Query("DELETE FROM recent WHERE id = :id")
    fun deleteById(id: String): Completable

}