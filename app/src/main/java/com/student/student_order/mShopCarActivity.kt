package com.student.student_order

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
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
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import java.util.*
import kotlin.collections.ArrayList


class mShopCarActivity : AppCompatActivity(), MfirebaeCallback {
    override fun getUserLogoutState(p0: Boolean) {
    }

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

    lateinit var mNameText: TextView
    lateinit var mPhoneText: TextView
    lateinit var mSpinner: Spinner
    lateinit var mNumTextView: EditText
    lateinit var mPriceTextView: TextView
    var mArray = ArrayList<String>()
    var mprice = ArrayList<String>()

    lateinit var mSendBtn: Button
    lateinit var mListView: QQListView
    lateinit var mNumBtn: Button
    var pirceArray: Int = 0
    var priceTotal = 0
    var mAdapter: BaseAdapter? = null

    var num: String = ""
    var total: Int = 0
    lateinit var mFirebselibClass: MfiebaselibsClass

    private var food: ArrayAdapter<String>? = null
    var mItem: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass = MfiebaselibsClass(this, this)

        setContentView(R.layout.activity_m_shop_car)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getItem()
        initView()
        setMember()
        getPrice()


    }

    fun getItem() {
        mArray = MySharedPrefernces.getArrayList(this)
        if (mArray.size != 0) {
            mArray.forEach {
                num = num + "\n" + it
            }
            Log.d("Jack", num)
        }

    }

    fun getPrice() {
        mprice = MySharedPrefernces.getPriceArrayList(this)
        mprice.forEach {
            total = total + it.toInt()

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
            mArray.removeAt(position)
            mprice.removeAt(position)
            mAdapter!!.notifyDataSetChanged()
            MySharedPrefernces.saveArrayList(this, mArray)
            MySharedPrefernces.savePriceArrayList(this, mprice)
            getPrice()
        })

        mNumBtn.setOnClickListener {
            priceTotal = pirceArray * mNumTextView.text.toString().toInt()

        }
        mSendBtn.setOnClickListener {
            var i = mItem + "," + priceTotal
            mArray.add(i)

            food!!.notifyDataSetChanged()

        }


    }

    fun setMember() {
        mNameText.text = MySharedPrefernces.getUserId(this)
        mPhoneText.text = MySharedPrefernces.getUserName(this)
    }

    fun addFirebase() {
        val mCal = Calendar.getInstance()
        val s = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());
        var mHasMap = HashMap<String, String>()
        var key = MySharedPrefernces.getId(this) + s
        mHasMap.put("id", MySharedPrefernces.getId(this))
        mHasMap.put("time", System.currentTimeMillis() + "")
        mHasMap.put("food", "")
        mFirebselibClass.setFireBaseDB(
            "https://order-3fe87.firebaseio.com/FavoriteList" + "/" + MySharedPrefernces.getId(
                this
            ), s.toString(), mHasMap
        )
    }
}
