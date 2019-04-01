package com.student.student_order

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.widget.LoginButton
import android.content.Intent



class LoginActivity : AppCompatActivity() {
    var callbackManager: CallbackManager? = null
    lateinit var mFbLoginBtn : LoginButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callbackManager = CallbackManager.Factory.create()
        AppEventsLogger.activateApp(this)
        FacebookSdk.sdkInitialize(applicationContext)

        setContentView(R.layout.activity_login)

        if (FacebookManager.checkFbState(this)){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }else{

        }
        mFbLoginBtn = findViewById(R.id.login_button)
        FacebookManager.printHashKey(this)
        FacebookManager.fbLogin(this,mFbLoginBtn,callbackManager,MainActivity::class.java)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }

}
