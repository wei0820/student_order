package com.student.student_order;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MyApi {
    private static final String TAG = "MyApi";
    public  static  String getDate(long time) {
        Log.d(TAG, "getDate: "+time);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("yyyy-MM-dd HH:mm:ss", cal).toString();
        return date;
    }


    public static void setFireBase(Context context) {
        Firebase.setAndroidContext(context);
        String url = MemberData.KEY_URL;

        Firebase mFirebaseRef = new Firebase(url);
        mFirebaseRef.child(MySharedPrefernces.getId(context)).addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                Member member = dataSnapshot.getValue(Member.class);
                Log.d(TAG, "onChildAdded: "+member.id);
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });


    }
}
