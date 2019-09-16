package com.hour24.hobby.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 상품 정보
 */
@Entity(tableName = "FavoriteEntity")
data class FavoriteEntity(

    // index
    @PrimaryKey var id: Int,

    // 이름
    var name: String,

    // 썸네일
    var thumbnail: String,

    // 설명
    var rate: Double,

    // 이미지 주소
    var imagePath: String,

    // 제목
    var subject: String,

    // 가격
    var price: Int,

    // 체크 여부
    var isChecked: Boolean = false

)