package com.hour24.hobby.view.detail

import androidx.databinding.ObservableField
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider


class DetailViewModel(private val mContextProvider: ContextProvider) {

    private val mModel = ObservableField<OfflineItemModel>()

    init {


    }

    fun setModel(model: OfflineItemModel) {
        mModel.set(model)
    }

    fun getModel() = mModel
}