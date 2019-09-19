package com.hour24.hobby.viewmodel

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.hour24.hobby.R
import com.hour24.hobby.consts.SignInConst
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.tryCatch
import timber.log.Timber

class SessionViewModel(private val mContextProvider: ContextProvider) {

    // google
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    /**
     * 구글 로그인 Instance 생성
     */
    fun onGoogleSigIn(activity: Activity) {
        tryCatch {
            // Configure Google Sign In
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(mContextProvider.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            mGoogleSignInClient = GoogleSignIn.getClient(mContextProvider.getContext(), gso)

            val intent = mGoogleSignInClient.signInIntent
            activity.startActivityForResult(intent, SignInConst.GOOGLE)
        }
    }

    /**
     * 구글 로그인 후 Firebase Auth 처리
     */
    fun onFirebaseAuthWithGoogle(activity: Activity, account: GoogleSignInAccount?) {
        tryCatch {
            if (account == null) {
                return
            }

            Timber.d(account.id)

            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance()
                .signInWithCredential(credential)
                .addOnCompleteListener(activity) { task ->
                    tryCatch {
                        if (task.isSuccessful) {

                        } else {
                            Timber.e(task.exception)
                        }
                    }
                }
        }
    }

    /**
     * Firebase 로그아웃
     */
    fun onFirebaseSignOut() {
        FirebaseAuth.getInstance().signOut()
    }
}