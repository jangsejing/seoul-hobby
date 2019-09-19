package com.hour24.tb.adapter


import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hour24.hobby.utils.tryCatch

/**
 * Generic RecyclerView Adapter for DataBinding
 *
 * @author jess
 * @since 2019-06-07
 */
@Suppress("UNCHECKED_CAST")
abstract class GenericRecyclerViewAdapter<T : Any, in D : ViewDataBinding>(
    private val layoutId: Int
) : RecyclerView.Adapter<GenericRecyclerViewAdapter.ViewHolder>() {

    private val list = mutableListOf<T>()

    abstract fun onBindData(position: Int, model: T, dataBinding: D)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding =
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        tryCatch {
            onBindData(position, list[position], holder.mDataBinding as D)
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    fun addAllItem(items: List<T>?) {
        items?.let {
            list.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun addItem(item: T) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): T {
        return list[position]
    }

    fun getList(): List<T> {
        return list
    }

    class ViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        var mDataBinding: ViewDataBinding = binding
    }

//    mAdapter = object : GenericRecyclerViewAdapter<Product, ListItemBinding>(activity, R.layout.list_item) {
//
//        override fun onBindData(position: Int, model: Product, dataBinding: ListItemBinding) {
//
//            try {
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        }
//    }
}
