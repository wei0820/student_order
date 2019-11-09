package com.student.student_order

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView
import android.widget.ArrayAdapter





class mShopCarActivity : AppCompatActivity() {
    lateinit var mNameText: TextView
    lateinit var mPhoneText: TextView
    lateinit var mSpinner: Spinner
    lateinit var mNumTextView: TextView
    lateinit var mPriceTextView: TextView
    var mArray = ArrayList<String>()
    lateinit var mSendBtn: Button
    lateinit var mListView: ListView

    private var food : ArrayAdapter<String>? =null
    var mItem :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_shop_car)
        initView()
        setMember()


    }

    fun initView() {
        mNameText = findViewById(R.id.nametext)
        mPhoneText = findViewById(R.id.phone)
        mSpinner = findViewById(R.id.spiner)
        mNumTextView = findViewById(R.id.num)
        mPriceTextView = findViewById(R.id.price)
        mSendBtn = findViewById(R.id.send)
        mListView = findViewById(R.id.listview)
        food = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, mArray
        )

        mListView.adapter = food
        val lunch = arrayListOf("雞腿飯", "魯肉飯", "排骨飯", "水餃", "陽春麵")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lunch)
        mSpinner.adapter = adapter

        mSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@mShopCarActivity,
                    "你選的是" + lunch[position],
                    Toast.LENGTH_SHORT
                ).show()
                mItem = lunch[position]
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        })
        mSendBtn.setOnClickListener {
            mArray.add(mItem)

            food!!.notifyDataSetChanged()

        }
        mListView.setOnItemLongClickListener(AdapterView.OnItemLongClickListener { arg0, arg1, pos, id ->
            // TODO Auto-generated method stub
            Log.d("Jack",mArray.get(pos))
            mArray.removeAt(pos)
            food!!.notifyDataSetChanged()
            true
        })

    }

    fun setMember() {

        mNameText.text = MySharedPrefernces.getUserId(this)
        mPhoneText.text = MySharedPrefernces.getUserName(this)
    }
}
