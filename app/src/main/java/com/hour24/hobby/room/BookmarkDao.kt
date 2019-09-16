package com.hour24.hobby.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark")
    fun selectAll(): Flowable<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmark where id = :id")
    fun selectById(id: String): Observable<BookmarkEntity>


    @Query("SELECT count(*) FROM bookmark where id = :id")
    fun selectCountById(id: String): Observable<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: BookmarkEntity): Completable

    @Query("DELETE FROM bookmark WHERE id = :id")
    fun deleteById(id: String): Completable

}