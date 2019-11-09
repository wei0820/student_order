package com.student.student_order

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView

class mShopCarActivity : AppCompatActivity() {
    lateinit var mNameText:TextView
    lateinit var mPhoneText :TextView
    lateinit var mSpinner: Spinner
    lateinit var mNumTextView: TextView
    lateinit var mPriceTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_shop_car)
        initView()
        setMember()


    }
    fun initView(){
        mNameText = findViewById(R.id.nametext)
        mPhoneText = findViewById(R.id.phone)
        mSpinner = findViewById(R.id.spiner)
        mNumTextView = findViewById(R.id.num)
        mPriceTextView = findViewById(R.id.price)

    }
    fun setMember(){

        mNameText.text = MySharedPrefernces.getUserId(this)
        mPhoneText.text = MySharedPrefernces.getUserName(this)
    }
}
