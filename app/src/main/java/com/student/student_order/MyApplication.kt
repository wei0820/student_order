package com.student.student_order

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class  MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(this)
    }

}