package com.raspberry.socialapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
    private var firebaseAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        callbackManager = CallbackManager.Factory.create()

        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Toast.makeText(applicationContext, R.string.com_facebook_loginview_cancel_action, Toast.LENGTH_SHORT)
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(applicationContext, "Error login", Toast.LENGTH_SHORT)
            }
        })


        firebaseAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                goMainScreen()
            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(accessToken: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)
        FirebaseAuth.getInstance()!!.signInWithCredential(credential).addOnCompleteListener(this) { task ->
            if (!task.isSuccessful) {

                Toast.makeText(applicationContext, "Error connecting firebase login", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun goMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance()!!.addAuthStateListener(firebaseAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        FirebaseAuth.getInstance()!!.removeAuthStateListener(firebaseAuthListener!!)
    }


}
