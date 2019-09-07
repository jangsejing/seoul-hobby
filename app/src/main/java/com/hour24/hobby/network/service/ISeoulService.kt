package com.hour24.hobby.network.service

import com.hour24.hobby.const.APIConst
import com.hour24.hobby.model.OfflineCourseModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ISeoulService {

    /**
     * 오프라인 강좌 리스트 조회
     */
    @GET("${APIConst.ApiKey.seoul}/json/OfflineCourse/{startIndex}/{endIndex}/{search}/{date}")
    fun reqOfflineCourse(
        @Path("startIndex") startIndex: Int,
        @Path("endIndex") endIndex: Int,
        @Path("search") search: String, // 데이터 없을 경우 " " 입력, "" 안됨
        @Path("date") date: String // yyyyMM
    ): Flowable<OfflineCourseModel>
}