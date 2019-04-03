package com.student.student_order

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback

class MemberActivity : AppCompatActivity() , MfirebaeCallback {
    override fun resetPassWordState(p0: Boolean) {

    }

    override fun getsSndPasswordResetEmailState(p0: Boolean) {
    }

    override fun getFirebaseStorageType(p0: String?, p1: String?) {
    }

    override fun getUpdateUserName(p0: Boolean) {
    }

    override fun getDatabaseData(p0: Any?) {

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
    lateinit var mNameTextView: TextView
    lateinit var mPointTextView: TextView
    lateinit var mTimeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mfiebaselibsClass = MfiebaselibsClass(this, this)

        setContentView(R.layout.content_member)
        initLayout()
        FacebookManager.checkFbState(this,mImageView,mIdTextView,mNameTextView)
        mfiebaselibsClass!!.getFirebaseDatabase("https://order-3fe87.firebaseio.com/MemberList/2249556051978557","2249556051978557")
    }


    fun  initLayout(){
        mImageView = findViewById(R.id.img)
        mIdTextView = findViewById(R.id.idtext)
        mNameTextView = findViewById(R.id.nametext)
        mPointTextView = findViewById(R.id.pointtext)
        mTimeTextView = findViewById(R.id.timetext)
        mTimeTextView.text = "上次登入時間:"+MyApi.getDate(MySharedPrefernces.getMyCardTime(this).toLong())

    }

}
