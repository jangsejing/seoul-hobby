package com.hour24.hobby.model

import com.hour24.hobby.utils.Utils


data class CommentModel(
    val items: List<CommentItem>? = null
) {
    constructor() : this(null)
}

data class CommentItem(
    val uid: String? = null, // 회원 아이디
    val name: String? = null, // 회원 이름
    val courseId: String? = null, // 강좌 아이디
    val text: String? = null, // 글
    val id: String = Utils.getRandomId(),
    val timeStamp: Long = System.currentTimeMillis() // 타임스탬프
) {
    constructor() : this(null, null, null, null)
}