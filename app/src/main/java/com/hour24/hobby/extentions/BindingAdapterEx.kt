package com.hour24.hobby.extentions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hour24.hobby.utils.tryCatch
import com.hour24.tb.adapter.GenericRecyclerViewAdapter

@BindingAdapter("addAllItem")
fun RecyclerView.addAllItem(
    list: List<Any>?
) {
    tryCatch {
        (this.adapter as? GenericRecyclerViewAdapter<Any, *>)?.run {
            this.addAllItem(list)
        }
    }
}