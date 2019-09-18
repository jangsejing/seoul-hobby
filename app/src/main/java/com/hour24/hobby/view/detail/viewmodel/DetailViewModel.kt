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
import com.hour24.hobby.singleton.Session
import com.hour24.hobby.utils.tryCatch
import timber.log.Timber


class DetailViewModel(
    private val mContextProvider: ContextProvider,
    private val mId: String
) {

    private val mCommentList = ObservableField<List<CommentItem>>()
    private var mIsClear: ObservableBoolean = ObservableBoolean()

    init {
        mIsClear.set(true)
        onReadComment()
    }

    private fun getDb() = FirebaseFirestore.getInstance()

    /**
     * Firebase에 댓글 등록
     *
     * @param text 글
     */
    fun onWriteComment(text: String) {

        if (!Session.isExist() || mId.isEmpty() || text.isEmpty()) {
            return
        }

        val map = hashMapOf<String, Any>()
        map[FirebaseConst.ITEMS] =
            arrayUnion(CommentItem(Session.getUid(), Session.getName(), mId, text))

        getDb().collection(FirebaseConst.COMMENT)
            .document(mId)
            .set(map, SetOptions.merge())
            .addOnSuccessListener {
                Timber.d("success : $mId / $text")
            }
            .addOnFailureListener {
                Timber.e(it)
            }
    }

    /**
     * 댓글 가져오기
     */
    private fun onReadComment() {

        if (mId.isEmpty()) {
            return
        }

//        getDb().collection(FirebaseConst.COMMENT).document(mId)
//            .get()
//            .addOnSuccessListener {
//                tryCatch {
//                    val list = it.toObject(CommentModel::class.java)
//                    Timber.d("list : ${list?.items}")
//                    mCommentList.set(list?.items)
//                }
//            }
//            .addOnFailureListener {
//                Timber.e(it)
//            }

        getDb().collection(FirebaseConst.COMMENT).document(mId)
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

    fun getId() = mId

    fun getList() = mCommentList

    fun isClear() = mIsClear

}