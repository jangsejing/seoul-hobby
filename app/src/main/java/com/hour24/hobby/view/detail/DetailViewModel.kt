package com.hour24.hobby.view.detail

import androidx.databinding.ObservableField
import com.google.firebase.database.FirebaseDatabase
import com.hour24.hobby.consts.FirebaseConst
import com.hour24.hobby.model.OfflineItemModel
import com.hour24.hobby.provider.ContextProvider
import timber.log.Timber


class DetailViewModel(private val mContextProvider: ContextProvider) {

    private val mModel = ObservableField<OfflineItemModel>()

    init {

    }

    fun getFirebase() = FirebaseDatabase.getInstance().reference

    /**
     * Firebase에 글 등록
     *
     * @param id 강좌 아이디
     * @param text 글
     */
    fun onSubmit(id: String, text: String) {
        getFirebase().child(FirebaseConst.COMMENT).child(id).setValue(text)
            .addOnSuccessListener {
                Timber.d("Success : $id / $text")
            }
            .addOnFailureListener {
                Timber.e(it)
            }
    }
}