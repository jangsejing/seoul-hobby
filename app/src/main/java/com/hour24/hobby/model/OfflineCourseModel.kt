package com.hour24.hobby.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


//{
//    "OfflineCourse": {
//    "list_total_count": 628,
//    "RESULT": {
//    "CODE": "INFO-000",
//    "MESSAGE": "정상 처리되었습니다"
//},
//    "row": [
//    {
//        "COURSE_ID": "ASP00001S1001201999248",
//        "COURSE_NM": "로마의 시인, 오비디우스의 발자취를 따라",
//        "COURSE_GUBUN": "오프라인",
//        "COURSE_REQUEST_STR_DT": "201909301000",
//        "COURSE_REQUEST_END_DT": "201909300000",
//        "COURSE_STR_DT": "20190927",
//        "COURSE_END_DT": "20191025",
//        "COURSE_TARGET": "",
//        "CAPACITY": 30,
//        "COURSE_APPLY_URL": ""
//    }
//    ]
//}
//}

data class OfflineCourseModel(
    @SerializedName("OfflineCourse")
    val offlineCourse: OfflineCourse?
) : Serializable

data class OfflineCourse(
    @SerializedName("list_total_count")
    val listTotalCount: Int,

    @SerializedName("RESULT")
    val result: Result,

    @SerializedName("row")
    val row: List<OfflineItemModel> = ArrayList()
) : Serializable

data class Result(
    @SerializedName("CODE")
    val code: String,

    @SerializedName("MESSAGE")
    val message: String
) : Serializable

data class OfflineItemModel(

    @SerializedName("COURSE_ID")
    val id: String,

    @SerializedName("COURSE_NM")
    val name: String,

    @SerializedName("COURSE_GUBUN")
    val type: String, // 강의구분

    @SerializedName("COURSE_REQUEST_STR_DT")
    val applyStartDate: String,

    @SerializedName("COURSE_REQUEST_END_DT")
    val applyEndDate: String,

    @SerializedName("COURSE_STR_DT")
    val courseStartDate: String,

    @SerializedName("COURSE_END_DT")
    val courseEndDate: String,

    @SerializedName("COURSE_TARGET")
    val target: String,

    @SerializedName("CAPACITY")
    val capacity: Int,

    @SerializedName("COURSE_APPLY_URL")
    val applyUrl: String

) : Serializable