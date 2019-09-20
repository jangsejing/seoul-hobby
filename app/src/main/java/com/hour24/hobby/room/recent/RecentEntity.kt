package com.hour24.hobby.room.recent

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 북마크
 */
@Entity(tableName = "recent")
data class RecentEntity(
    @PrimaryKey var id: String, // courseId
    val data: String // json
)