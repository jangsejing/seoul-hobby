package com.hour24.hobby.singleton

import com.google.firebase.auth.FirebaseAuth

object Session {

    /**
     * 로그인 여부
     */
    fun isExist() = FirebaseAuth.getInstance().currentUser != null

    /**
     * 이름
     */
    fun getName(): String? = FirebaseAuth.getInstance().currentUser?.displayName

    /**
     * uid
     */
    fun getUid(): String? = FirebaseAuth.getInstance().currentUser?.uid

}