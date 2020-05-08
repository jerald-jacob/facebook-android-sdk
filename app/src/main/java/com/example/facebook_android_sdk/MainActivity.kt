package com.example.facebook_android_sdk

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton


class MainActivity : AppCompatActivity() {
    private var info: TextView? = null
    private var loginButton: LoginButton? = null
    private var callbackManager: CallbackManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FacebookSdk.sdkInitialize(applicationContext)
        callbackManager = CallbackManager.Factory.create()
        setContentView(R.layout.activity_main)
        info = findViewById<View>(R.id.info) as TextView
        loginButton = findViewById<View>(R.id.login_button) as LoginButton
        loginButton!!.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                info!!.text =
                    "User ID: " + loginResult.accessToken.userId + "\n" + "Auth Token: " + loginResult.accessToken.token
            }

            override fun onCancel() {
                info!!.text = "Login attempt canceled."
            }

            override fun onError(e: FacebookException) {
                info!!.text = "Login attempt failed."
            }
        })
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }
}