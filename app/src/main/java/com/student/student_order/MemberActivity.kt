package com.student.student_order

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView
import android.widget.TextView

class MemberActivity : AppCompatActivity() {
    lateinit var mImageView: ImageView
    lateinit var mIdTextView: TextView
    lateinit var mNameTextView: TextView
    lateinit var mPointTextView: TextView
    lateinit var mTimeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_member)
        initLayout()
        FacebookManager.checkFbState(this,mImageView,mIdTextView,mNameTextView)

    }


    fun  initLayout(){
        mImageView = findViewById(R.id.img)
        mIdTextView = findViewById(R.id.idtext)
        mNameTextView = findViewById(R.id.nametext)
        mPointTextView = findViewById(R.id.pointtext)
        mTimeTextView = findViewById(R.id.timetext)

    }

}
