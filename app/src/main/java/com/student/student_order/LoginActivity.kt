package com.student.student_order

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.widget.LoginButton


class LoginActivity : AppCompatActivity(){

    val list = listOf<String>(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.INTERNET,
        android.Manifest.permission.ACCESS_WIFI_STATE,
        android.Manifest.permission.ACCESS_NETWORK_STATE)

    var callbackManager: CallbackManager? = null
    lateinit var mFbLoginBtn : LoginButton
    private val MY_PERMISSIONS_REQUEST_LOCATION = 1


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
        checkPermission()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }
    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_LOCATION)
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "需要定位功能,才能使用喔", Toast.LENGTH_SHORT).show()
                return
            }
        }
    }


}
