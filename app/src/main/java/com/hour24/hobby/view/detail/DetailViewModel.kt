package com.hour24.hobby.view.detail

import androidx.databinding.ObservableField
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.hour24.hobby.consts.FirebaseConst
import com.hour24.hobby.model.CommentModel
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider
import timber.log.Timber
import com.google.firebase.firestore.FieldValue.arrayUnion


class DetailViewModel(private val mContextProvider: ContextProvider) {

    private val mModel = ObservableField<OfflineItemModel>()

    init {

    }

    private fun getDb() = FirebaseFirestore.getInstance()

    /**
     * Firebase에 글 등록
     *
     * @param id 강좌 아이디
     * @param text 글
     */
    fun onSubmit(id: String, text: String) {

        val data = CommentModel("uid", id, text)

        val map = hashMapOf<String, Any>()
        map[FirebaseConst.ITEMS] = arrayUnion(data)

        getDb().collection(FirebaseConst.COMMENT)
            .document(id)
            .set(map)
            .addOnSuccessListener {

            }
            .addOnFailureListener {
                Timber.e(it)
            }
    }
}