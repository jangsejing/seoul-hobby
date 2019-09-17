package com.hour24.hobby.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.hour24.hobby.R
import com.hour24.hobby.consts.SignInConst
import com.hour24.hobby.utils.tryCatch
import timber.log.Timber


abstract class BaseActivity : AppCompatActivity() {

    abstract fun initLayout()

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * 구글 로그인 세션 시작
     */
    fun onGoogleSigIn() {
        tryCatch {
            // Configure Google Sign In
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

            val intent = mGoogleSignInClient.signInIntent
            startActivityForResult(intent, SignInConst.GOOGLE)
        }
    }

    /**
     * 로그인 결과
     */
    private fun onFirebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        tryCatch {

            if (account == null) {
                return
            }

            Timber.d(account.id)

            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    tryCatch {
                        if (task.isSuccessful) {

                        } else {

                        }
                    }
                }

        }
    }

    /**
     * 로그아웃
     */
    fun onFirebaseSignOut() {
        FirebaseAuth.getInstance().signOut()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        tryCatch {
            when (requestCode) {
                SignInConst.GOOGLE -> {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    if (task.isSuccessful) {
                        val account = task.getResult(ApiException::class.java)
                        onFirebaseAuthWithGoogle(account)
                    } else {
                        Timber.e(task.exception)
                    }
                }
            }
        }
    }


}
