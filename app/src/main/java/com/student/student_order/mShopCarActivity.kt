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
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter









class mShopCarActivity : AppCompatActivity() {
    lateinit var mNameText: TextView
    lateinit var mPhoneText: TextView
    lateinit var mSpinner: Spinner
    lateinit var mNumTextView: EditText
    lateinit var mPriceTextView: TextView
    var mArray = ArrayList<String>()
    var mprice = ArrayList<String>()

    lateinit var mSendBtn: Button
    lateinit var mListView: QQListView
    lateinit var mNumBtn :Button
    var pirceArray :Int = 0
    var priceTotal =0
     var mAdapter: BaseAdapter? = null

    var num :String = ""
    var total :Int = 0

    private var food : ArrayAdapter<String>? =null
    var mItem :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_m_shop_car)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mArray = MySharedPrefernces.getArrayList(this)
        initView()
        setMember()
        getPrice()


    }
    fun getPrice(){
        mprice = MySharedPrefernces.getPriceArrayList(this)
        mprice.forEach {
            total = total + it.toInt()
            Log.d("Jack", total.toString())

        }
        mPriceTextView.text = total.toString()

        total = 0

    }

    fun initView() {
        mNameText = findViewById(R.id.nametext)
        mPhoneText = findViewById(R.id.phone)
        mSpinner = findViewById(R.id.spiner)
        mNumTextView = findViewById(R.id.num)
        mPriceTextView = findViewById(R.id.price)
        mSendBtn = findViewById(R.id.send)
        mListView = findViewById(R.id.id_listview)
        mNumBtn = findViewById(R.id.numbtn)

        mAdapter = object : ArrayAdapter<String>(
            this,
            -1, mArray
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var convertView = convertView
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
                }
                val tv = convertView!!.findViewById(R.id.id_text) as TextView
                tv.text = getItem(position)
                return convertView
            }
        }
        mListView.setAdapter(mAdapter)
        mListView.setOnItemRightViewClickListener(QQListView.OnItemRightViewClickListener { position, view ->
            Log.d("TAG", "remove item")
            mArray.removeAt(position)
            mprice.removeAt(position)
            mAdapter!!.notifyDataSetChanged()
            MySharedPrefernces.saveArrayList(this,mArray)
            MySharedPrefernces.savePriceArrayList(this,mprice)
            getPrice()
        })

        mNumBtn.setOnClickListener {
            priceTotal = pirceArray * mNumTextView.text.toString().toInt()

        }
        mSendBtn.setOnClickListener {
            var i = mItem +","+priceTotal
            mArray.add(i)

            food!!.notifyDataSetChanged()

        }


    }

    fun setMember() {
        mNameText.text = MySharedPrefernces.getUserId(this)
        mPhoneText.text = MySharedPrefernces.getUserName(this)
    }
}
