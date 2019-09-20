package com.hour24.hobby.utils

import com.google.gson.Gson
import com.hour24.hobby.model.CourseItem

object GsonUtils {

    fun toJson(model: CourseItem): String {
        return Gson().toJson(model)
    }
}