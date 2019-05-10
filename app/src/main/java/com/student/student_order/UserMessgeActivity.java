package com.student.student_order;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.app.Activity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class UserMessgeActivity extends Activity implements View.OnClickListener {
    private TextView userMsgText;
    private EditText userMsgEdt;
    private Button  userMsgBtn;
    String id ="11111";
    String name = "2222";
    String  tomsg= "";
    CharSequence s;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_messge);
        getWindow().setFormat(PixelFormat.TRANSPARENT);
        getFireBase();
        getUserid();
        initLayout();
        setFireBase();
    }

    private static final String TAG = "UserMessgeActivity";
    private void getUserid() {
        Bundle bundle =this.getIntent().getExtras();

        id = "20190510";
        name ="店家";
        Calendar mCal = Calendar.getInstance();
        s = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());
    }

    private void initLayout() {
        userMsgText = (TextView) findViewById(R.id.usermsgtext);
        userMsgEdt = (EditText) findViewById(R.id.usermsgedt);
        findViewById(R.id.usermsgbtn).setOnClickListener(this);
    }
    private void toMsg(final String msg) {
        String value = tomsg;
        String Usrmsg=MySharedPrefernces.getUserId(this)+"對"+name+"說："+"\t"+msg+"\t"+"時間:"+s;
        String tomsg = Usrmsg+"\n"+value;
        String url = "https://order-3fe87.firebaseio.com/foodList";
        Firebase mFirebaseRef = new Firebase(url);

        Firebase countRef = mFirebaseRef.child(id);
        Map<String,Object>nameMap = new HashMap<>();
        nameMap.put("tomsg", tomsg);
        countRef.updateChildren(nameMap);
        countRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                progressDialog = ProgressDialog.show(UserMessgeActivity.this, "已經留言給他囉", "資料更新中！！", false, false, new DialogInterface.OnCancelListener() {
//
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        //
//                    }
//                });

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
//        countRef.runTransaction(new Transaction.Handler() {
//
//            @Override
//            public Transaction.Result doTransaction(MutableData currentData) {
////
////                if (currentData.getValue() == null) {
////                    currentData.setValue("");
////                    userMsgText.setText(currentData.getValue().toString());
////                } else {
//                    String value = currentData.getValue().toString();
//                    String Usrmsg = msg+"\t"+s;
//                    String tomsg = Usrmsg+"\n"+value;
//                    currentData.setValue(tomsg);
//                    userMsgText.setText(currentData.getValue().toString());
////
////                }
//                return Transaction.success(currentData); //we can also abort by calling Transaction.abort()
//            }
//
//            @Override
//            public void onComplete(FirebaseError firebaseError, boolean committed, DataSnapshot currentData) {
//                //This method will be called once with the results of the transaction.
//            }
//        });

    }
    @Override
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.usermsgbtn:
                if(userMsgEdt.getText().toString().trim().equals("")&&userMsgEdt.getText().toString().trim()==null){
                    Toast.makeText(UserMessgeActivity.this,"不能輸入空白喔！",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    toMsg(userMsgEdt.getText().toString().trim());
                    userMsgEdt.setText("");
                    Toast.makeText(UserMessgeActivity.this,"已經留言給他囉！",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserMessgeActivity.this,MainActivity.class));
                    this.finish();
                }


//                    setFireBase();
                break;

        }

    }

    private void setFireBase() {
        Firebase.setAndroidContext(this);
        String url = "https://order-3fe87.firebaseio.com/foodList";

        Firebase mFirebaseRef = new Firebase(url);


//		if(Firebase.getDefaultConfig().isPersistenceEnabled()==false)mFirebaseRef.getDefaultConfig().setPersistenceEnabled(true);
        mFirebaseRef.orderByChild(id).equalTo("tomsg").addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Log.d(TAG, "onChildAdded: " + dataSnapshot.getValue().toString());
//				Log.d(TAG, "onChildAdded: "+ (String) dataSnapshot.child("tittle").getValue());
//				Log.d(TAG, "onChildAdded: "+ (Long) dataSnapshot.child("message").getValue());
//				TaipeiZoo taipeiZoo = new TaipeiZoo();
//				taipeiZoo.setName((String)dataSnapshot.child("name").getValue());
//                for (DataSnapshot alert: dataSnapshot.getChildren()) {
//                    Log.d(TAG, "onChildAdded: "+alert.getValue().toString());
//                    list.add(0,gayPlace);
////                    for (DataSnapshot recipient: alert.child("formsg").getChildren()) {
////                        Log.d(TAG, "onChildAdded: "+recipient.getValue().toString());
////                    }
//
                ResultData resultData = dataSnapshot.getValue(ResultData.class);
                Log.d(TAG, "onChildAdded: "+resultData.tomsg);
                userMsgText.setText(resultData.tomsg);

                progressDialog.dismiss();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "onChildChanged: " + "onChildChanged");

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });


    }


    private void getFireBase() {
        Firebase.setAndroidContext(this);
        String url = "https://order-3fe87.firebaseio.com/foodList";

        Firebase mFirebaseRef = new Firebase(url);


//		if(Firebase.getDefaultConfig().isPersistenceEnabled()==false)mFirebaseRef.getDefaultConfig().setPersistenceEnabled(true);
        mFirebaseRef.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "onChildAdded: " + dataSnapshot.getValue().toString());
//				Log.d(TAG, "onChildAdded: "+ (String) dataSnapshot.child("tittle").getValue());
//				Log.d(TAG, "onChildAdded: "+ (Long) dataSnapshot.child("message").getValue());
//				TaipeiZoo taipeiZoo = new TaipeiZoo();
//				taipeiZoo.setName((String)dataSnapshot.child("name").getValue());
//                for (DataSnapshot alert: dataSnapshot.getChildren()) {
//                    Log.d(TAG, "onChildAdded: "+alert.getValue().toString());
//                    list.add(0,gayPlace);
////                    for (DataSnapshot recipient: alert.child("formsg").getChildren()) {
////                        Log.d(TAG, "onChildAdded: "+recipient.getValue().toString());
////                    }
//
                ResultData resultData = dataSnapshot.getValue(ResultData.class);
                tomsg =resultData.tomsg;
//                Log.d(TAG, "onChildAdded: "+resultData.tomsg);

                userMsgText.setText(resultData.tomsg);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "onChildChanged: " + "onChildChanged");

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });


    }

}
