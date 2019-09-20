package com.hour24.hobby.view.detail.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.google.firebase.firestore.FieldValue.arrayUnion
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.hour24.hobby.consts.FirebaseConst
import com.hour24.hobby.model.CommentItem
import com.hour24.hobby.model.CommentModel
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.tryCatch
import com.hour24.hobby.viewmodel.BaseViewModel
import com.hour24.hobby.viewmodel.Session
import timber.log.Timber

class DetailViewModel(
    private val mContextProvider: ContextProvider,
    private val mCourseId: String
) : BaseViewModel(mContextProvider) {

    private val mCommentList = ObservableField<List<CommentItem>>()
    private val mComment = ObservableField<CommentItem>()
    private val mText = ObservableField<String>()
    private var mIsClear: ObservableBoolean = ObservableBoolean()
    private var mIsEdit: ObservableBoolean = ObservableBoolean()

    init {
        mIsClear.set(true)
        mIsEdit.set(false)
        onReadComment()
    }

    /**
     * 댓글 가져오기
     */
    private fun onReadComment() {

        if (mCourseId.isEmpty()) {
            return
        }

        FirebaseFirestore.getInstance()
            .collection(FirebaseConst.COMMENT).document(mCourseId)
            .addSnapshotListener { snapshot, e ->
                tryCatch {

                    if (e != null) {
                        Timber.e(e)
                        return@addSnapshotListener
                    }

                    if (snapshot == null || !snapshot.exists()) {
                        return@addSnapshotListener
                    }

                    val list = snapshot.toObject(CommentModel::class.java)?.items
                    Timber.d("list : $list")
                    mCommentList.set(list)
                }
            }
    }

    fun onSubmit() {
        if (!mIsEdit.get()) {
            onWriteComment()
        } else {
//            onEditComment()
        }
    }

    /**
     * 댓글 등록
     */
    private fun onWriteComment() {

        if (!Session.isExist()) {
            getSessionVM().onGoogleSigIn()
            return
        }

        if (mCourseId.isEmpty() || mText.get().isNullOrEmpty()) {
            return
        }

        val map = hashMapOf<String, Any>()
        map[FirebaseConst.ITEMS] =
            arrayUnion(CommentItem(Session.getUid(), Session.getName(), mCourseId, mText.get()))
        Timber.d("write : $map")

        FirebaseFirestore.getInstance()
            .collection(FirebaseConst.COMMENT)
            .document(mCourseId)
            .set(map, SetOptions.merge())
            .addOnSuccessListener {
                Timber.d("write success : $map")
                mText.set("")
            }
            .addOnFailureListener {
                Timber.e(it)
            }
    }


//    /**
//     * 댓글 수정
//     */
//    private fun onEditComment() {
//
//        if (!Session.isExist()) {
//            getSessionVM().onGoogleSigIn()
//            return
//        }
//
//        if (mText.get().isNullOrEmpty()) {
//            return
//        }
//
//        val model = mComment.get() ?: return
//        model.text = mText.get()
//
//        val map = hashMapOf<String, Any>()
//        map[FirebaseConst.ITEMS] =
//            arrayUnion(model)
//        Timber.d("edit : $map")
//
//        FirebaseFirestore.getInstance()
//            .collection(FirebaseConst.COMMENT)
//            .document(mComment.get()?.courseId.toString())
//            .set(map, SetOptions.merge())
//            .addOnSuccessListener {
//                Timber.d("edit success $map")
//            }
//            .addOnFailureListener {
//                Timber.e(it)
//            }
//    }

    fun getText() = mText

    fun getList() = mCommentList

    fun isClear() = mIsClear

    fun setTextForEdit(model: CommentItem) {
        mComment.set(model)
        mText.set(model.text)
        mIsEdit.set(true)
    }

}