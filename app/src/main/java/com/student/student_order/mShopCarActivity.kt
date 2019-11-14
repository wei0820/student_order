package com.student.student_order

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
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
    lateinit var mPriceTextView: TextView
    var mArray = ArrayList<String>()
    var mprice = ArrayList<String>()

    lateinit var mSendBtn: Button
    lateinit var mListView: QQListView
    var mAdapter: BaseAdapter? = null

    var num: String = ""
    var total: Int = 0
    lateinit var mFirebselibClass: MfiebaselibsClass
    lateinit var msendshop :Button
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
            num = ""
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
        msendshop = findViewById(R.id.sendshop)
        mNameText = findViewById(R.id.nametext)
        mPhoneText = findViewById(R.id.phone)
        mSpinner = findViewById(R.id.spiner)
        mPriceTextView = findViewById(R.id.price)
        mListView = findViewById(R.id.id_listview)
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
            getItem()
        })



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
        mHasMap.put("time", System.currentTimeMillis().toString())
        mHasMap.put("food", "")
        mFirebselibClass.setFireBaseDB(
            "https://order-3fe87.firebaseio.com/FavoriteList" + "/" + MySharedPrefernces.getId(
                this
            ), s.toString(), mHasMap
        )
    }
}
