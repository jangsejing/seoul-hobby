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
import com.hour24.hobby.provider.ContextProvider
import com.hour24.hobby.utils.tryCatch
import com.hour24.hobby.viewmodel.SessionViewModel
import timber.log.Timber


abstract class BaseActivity : AppCompatActivity() {

    abstract fun initLayout()

    private val mSessionVM by lazy {
        SessionViewModel(ContextProvider(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        tryCatch {
            when (requestCode) {
                SignInConst.GOOGLE -> {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    if (task.isSuccessful) {
                        mSessionVM.onFirebaseAuthWithGoogle(
                            this,
                            task.getResult(ApiException::class.java)
                        )
                    } else {
                        Timber.e(task.exception)
                    }
                }
            }
        }
    }
}
