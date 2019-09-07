package com.hour24.hobby.viewmodel

import com.hour24.hobby.R
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.DateUtils

class CourseItemViewModel(private val contextProvider: ContextProvider) {

    private lateinit var model: OfflineItemModel

    fun setModel(model: OfflineItemModel) {
        this.model = model
    }

    fun getModel() = model

    fun getName(): String {
        val name = model.name.trim()
        return if (name.isEmpty()) contextProvider.getString(R.string.main_course_name_empty) else name
    }
}