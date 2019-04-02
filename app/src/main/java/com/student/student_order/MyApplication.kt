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
            MySharedPrefernces.saveMyCardTime(getApplicationContext(),System.currentTimeMillis().toString());


//依照設定格式取得字串
        } else {

           var last: Long = (MySharedPrefernces.getMyCardTime(getApplicationContext())).toLong()
           var now : Long =System.currentTimeMillis()
            if(now-last>=24* 60 * 60 * 1000){
                MySharedPrefernces.saveMyCardTime(getApplicationContext(),"")
                MySharedPrefernces.saveUserKm(getApplicationContext(),"")
                MySharedPrefernces.saveUserStep(getApplicationContext(),"")
                MySharedPrefernces.saveUserDhot(getApplicationContext(),"")
                MySharedPrefernces.saveUserhot(getApplicationContext(),"")

            }


        }


    }

}