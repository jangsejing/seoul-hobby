package com.hour24.hobby.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 북마크
 */
@Entity(tableName = "bookmark")
data class BookmarkEntity(
    @PrimaryKey var id: String, // courseId
    val data: String // json
)