package com.hour24.hobby.room.session

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 세션
 */
@Entity(tableName = "session")
data class SessionEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val email: String,
    val token: String
)