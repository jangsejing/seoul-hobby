package com.hour24.hobby.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM FavoriteEntity")
    fun selectAll(): Single<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: FavoriteEntity): Completable

    @Query("DELETE FROM FavoriteEntity WHERE id = :id")
    fun deleteById(id: Int): Completable

}