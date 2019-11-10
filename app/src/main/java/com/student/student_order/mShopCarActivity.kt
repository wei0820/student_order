package com.student.student_order

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import android.widget.AdapterView
import android.widget.ArrayAdapter





class mShopCarActivity : AppCompatActivity() {
    lateinit var mNameText: TextView
    lateinit var mPhoneText: TextView
    lateinit var mSpinner: Spinner
    lateinit var mNumTextView: EditText
    lateinit var mPriceTextView: TextView
    var mArray = ArrayList<String>()
    lateinit var mSendBtn: Button
    lateinit var mListView: ListView
    lateinit var mNumBtn :Button
    var pirceArray :Int = 0
    var priceTotal =0


    private var food : ArrayAdapter<String>? =null
    var mItem :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_m_shop_car)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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
        mNumBtn = findViewById(R.id.numbtn)
        food = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, mArray
        )

        mListView.adapter = food
        val lunch = arrayListOf("雞腿飯", "魯肉飯", "排骨飯", "水餃", "陽春麵")
        val pirce = arrayListOf(50, 60, 70, 80,90)
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
                pirceArray = pirce[position]
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        })
        mNumBtn.setOnClickListener {
            priceTotal = pirceArray * mNumTextView.text.toString().toInt()
            mPriceTextView.text =  priceTotal.toString()

        }
        mSendBtn.setOnClickListener {
            var i = mItem +","+priceTotal
            mArray.add(i)

            food!!.notifyDataSetChanged()

        }
        mListView.setOnItemLongClickListener(AdapterView.OnItemLongClickListener { arg0, arg1, pos, id ->
            // TODO Auto-generated method stub
            val show = AlertDialog.Builder(this)
                .setTitle("訂單")//設定視窗標題
                .setMessage("是否刪除該筆訂單")//設定顯示的文字
                .setPositiveButton(
                    "是"
                ) { dialog, which ->
                    mArray.removeAt(pos)
                    food!!.notifyDataSetChanged()
                    dialog.dismiss()
                }//設定結束的子視窗
                .setNegativeButton("否") { dialog, which ->
                    dialog.dismiss()

                }
                .show()//呈現對話視窗

            true
        })

    }

    fun setMember() {

        mNameText.text = MySharedPrefernces.getUserId(this)
        mPhoneText.text = MySharedPrefernces.getUserName(this)
    }
}
