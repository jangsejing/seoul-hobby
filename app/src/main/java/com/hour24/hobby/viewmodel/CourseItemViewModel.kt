package com.hour24.hobby.viewmodel

import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider

class CourseItemViewModel(contextProvider: ContextProvider) {

    private lateinit var model: OfflineItemModel

    fun setModel(model: OfflineItemModel) {
        this.model = model
    }

    fun getModel() = model

}