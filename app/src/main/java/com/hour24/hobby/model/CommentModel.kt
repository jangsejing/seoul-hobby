package com.hour24.hobby.model

import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class CommentModel(
//    @PropertyName("items")
    val items: List<CommentItem>? = null
) {
    constructor() : this(null)
}

data class CommentItem(
//    @PropertyName("uid")
    val uid: String? = null,

//    @PropertyName("name")
    val name: String? = null,

//    @PropertyName("id")
    val id: String? = null, // 강좌 아이디

//    @PropertyName("text")
    val text: String? = null, // 글

//    @PropertyName("timeStamp")
    val timeStamp: Long = System.currentTimeMillis() // 타임스탬프
) {
    constructor() : this(null, null, null, null)
}