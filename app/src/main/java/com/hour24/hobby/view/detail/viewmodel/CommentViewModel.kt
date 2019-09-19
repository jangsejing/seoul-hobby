package com.hour24.hobby.view.detail.viewmodel

import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.hour24.hobby.R
import com.hour24.hobby.consts.FirebaseConst
import com.hour24.hobby.model.CommentItem
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.view.extentions.getDP
import timber.log.Timber

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

    /**
     * 댓글 삭제
     */
    private fun onDeleteComment(model: CommentItem) {
        val map = hashMapOf<String, Any>()
        map[FirebaseConst.ITEMS] = FieldValue.arrayRemove(model)

        FirebaseFirestore.getInstance()
            .collection(FirebaseConst.COMMENT)
            .document(model.courseId.toString())
            .update(map)
            .addOnSuccessListener {
                Timber.d("delete : $model")
            }
            .addOnFailureListener {
                Timber.e(it)
            }
    }

    fun onClick(v: View, model: CommentItem, detailVM: DetailViewModel) {
        when (v.id) {
            R.id.iv_more -> {

                val menus = arrayListOf(
//                    mContextProvider.getString(R.string.comment_edit),
                    mContextProvider.getString(R.string.comment_delete)
                )

                ListPopupWindow(mContextProvider.getContext()).apply {
                    width = mContextProvider.getContext().getDP(70)
                    anchorView = v
                    verticalOffset = -mContextProvider.getContext().getDP(10)
                    horizontalOffset = -mContextProvider.getContext().getDP(50)
                    setAdapter(
                        ArrayAdapter<String>(
                            mContextProvider.getContext(),
                            R.layout.detail_comment_popup,
                            menus
                        )
                    )
                    setOnItemClickListener { _, _, position, _ ->
                        when (position) {
                            0 -> {
                                detailVM.setTextForEdit(model)
                            }

                            1 -> {
                                onDeleteComment(model)
                            }
                        }
                        dismiss()
                    }
                    show()
                }
            }

        }
    }


}