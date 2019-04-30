package com.student.student_order

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.firebase.client.Firebase

class  MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(this)
        Firebase.setAndroidContext(this)
        checkTime()
    }

    fun  checkTime(){
        if (MySharedPrefernces.getMyCardTime(getApplicationContext()).equals("")) {
            System.currentTimeMillis()
            MySharedPrefernces.saveMyCardTime(getApplicationContext(),System.currentTimeMillis().toString())

        } else {

           var last: Long = (MySharedPrefernces.getMyCardTime(getApplicationContext())).toLong()
           var now : Long =System.currentTimeMillis()
            if(now-last>=24* 60 * 60 * 1000){
                MySharedPrefernces.saveMyCardTime(getApplicationContext(),"")

            }


        }


    }

}