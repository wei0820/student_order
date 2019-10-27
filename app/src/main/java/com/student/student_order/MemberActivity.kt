package com.student.student_order

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.google.gson.Gson
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import java.util.ArrayList

class MemberActivity : AppCompatActivity(), MfirebaeCallback {
    override fun resetPassWordState(p0: Boolean) {

    }

    override fun getsSndPasswordResetEmailState(p0: Boolean) {
    }

    override fun getFirebaseStorageType(p0: String?, p1: String?) {
    }

    override fun getUpdateUserName(p0: Boolean) {
    }

    override fun getDatabaseData(p0: Any?) {
////
        Log.d("getDatabaseData",p0.toString())
        arrayList.add(p0.toString())
        Log.d("getDatabaseData",arrayList.size.toString())
        if (arrayList.size>=5){
            MySharedPrefernces.saveUserName(this,arrayList.get(2).toString())
        }

//        var member  = Member()
////        var memberData = MemberData()
//        var gson = Gson()
//        member = gson.fromJson(p0.toString(),Member::class.java)
//        if (memberData!=null){
//            if (memberData.phone!=null){
//                mPointTextView.setText(memberData.phone)
//            }else{
//                mPointTextView.setText("請輸入電話")
//
//            }
//        }

    }

    override fun getuserLoginEmail(p0: String?) {
    }

    override fun getDeleteState(p0: Boolean, p1: String?, p2: Any?) {
    }

    override fun getFireBaseDBState(p0: Boolean, p1: String?) {
    }

    override fun getuseLoginId(p0: String?) {
    }

    override fun createUserState(p0: Boolean) {
    }

    override fun useLognState(p0: Boolean) {
    }

    override fun getFirebaseStorageState(p0: Boolean) {
    }

    override fun getUserLogoutState(p0: Boolean) {

    }

    private var mfiebaselibsClass: MfiebaselibsClass? = null
    lateinit var mImageView: ImageView
    lateinit var mIdTextView: TextView
    lateinit var mNameTextView: EditText
    lateinit var mPointTextView: EditText
    lateinit var mUpdatebtn: Button
     var arrayList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mfiebaselibsClass = MfiebaselibsClass(this, this)
        mfiebaselibsClass!!.getFirebaseDatabase("https://order-3fe87.firebaseio.com/MemberList/"+MySharedPrefernces.getId(this),MySharedPrefernces.getId(this))
        setContentView(R.layout.content_member)
        initLayout()
        FacebookManager.checkFbState(this, mImageView, mIdTextView, mNameTextView)
//        MyApi.setFireBase(this)
    }


    fun initLayout() {
        mImageView = findViewById(R.id.img)
        mIdTextView = findViewById(R.id.idtext)
        mNameTextView = findViewById(R.id.nametext)
        mPointTextView = findViewById(R.id.pointtext)
        mPointTextView.setText(MySharedPrefernces.getUserName(this))
        mUpdatebtn = findViewById(R.id.updatebtn)

        mUpdatebtn.setOnClickListener {
            if (mNameTextView.text.isEmpty() && mPointTextView.text.isEmpty()) {

            } else {
                setMemberData(
                    MySharedPrefernces.getId(this),
                    mNameTextView.text.toString(),
                    mPointTextView.text.toString()
                )
            }
        }

    }

    private fun setMemberData(
        Key: String, name: String,
        phone: String
    ) {
        val memberMap = HashMap<String, String>()
        memberMap.put(MemberData.KEY_ID, Key)
        memberMap.put(MemberData.KEY_NAME, name)
        memberMap.put(MemberData.KEY_PHONE, phone)
        MySharedPrefernces.saveUserName(this,phone)
        mfiebaselibsClass!!.setFireBaseDB(MemberData.KEY_URL, Key, memberMap)


    }

    private fun setFireBase() {
        Firebase.setAndroidContext(this)
        val url = MemberData.KEY_URL

        val mFirebaseRef = Firebase(url)
        mFirebaseRef.child(MySharedPrefernces.getId(this))
            .addChildEventListener(object : com.firebase.client.ChildEventListener {
                override fun onChildAdded(
                    dataSnapshot: com.firebase.client.DataSnapshot,
                    s: String
                ) {
                    Log.d("Jack", s)

                }

                override fun onChildChanged(
                    dataSnapshot: com.firebase.client.DataSnapshot,
                    s: String
                ) {

                }

                override fun onChildRemoved(dataSnapshot: com.firebase.client.DataSnapshot) {

                }

                override fun onChildMoved(
                    dataSnapshot: com.firebase.client.DataSnapshot,
                    s: String
                ) {

                }

                override fun onCancelled(firebaseError: FirebaseError) {

                }

            })


    }

}
