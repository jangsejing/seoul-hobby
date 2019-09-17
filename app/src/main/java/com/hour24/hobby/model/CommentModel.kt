package com.hour24.hobby.model

import java.io.Serializable


data class CommentModel(
    val uid: String?,
    val id: String,
    val text: String?,
    val timeStamp: Long = System.currentTimeMillis()
) : Serializable