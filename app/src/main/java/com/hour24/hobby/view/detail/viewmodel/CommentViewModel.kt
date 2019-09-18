package com.hour24.hobby.view.detail.viewmodel

import com.hour24.hobby.model.CommentItem
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider


class CommentViewModel(
    private val mContextProvider: ContextProvider
) {

    private var mModel = CommentItem()

    init {

    }

    fun setModel(model: CommentItem) {
        this.mModel = model
    }

    fun getModel() = mModel

}