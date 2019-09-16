package com.hour24.hobby.utils

import com.google.gson.Gson
import com.hour24.hobby.model.OfflineItemModel

object GsonUtils {

    fun toJson(model: OfflineItemModel): String {
        return Gson().toJson(model)
    }
}