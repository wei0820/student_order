package com.student.student_order

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import java.io.File
import java.util.*


class BigMannerDetailActivity : AppCompatActivity(), View.OnClickListener, MfirebaeCallback {
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

    private val CAMERA = 66
    private val PHOTO = 99
    private val REQUEST_EXTERNAL_STORAGE = 200
    private val PICKER = 100
    lateinit var phone: DisplayMetrics
    lateinit var mAddressEdt: EditText
    lateinit var mCheckBtn: Button
    lateinit var mSpinner: TextView
    lateinit var mStartbtn: Button
    lateinit var mEndbtn: Button
    lateinit var mPriceEdt: EditText
    lateinit var mPhotoButtton: Button
    lateinit var mPhoneEdt: EditText
    lateinit var mMessageEdt: EditText
    lateinit var mSendBtn: Button
    var mSelectType : String = ""
    var mType : Int = 0

    var mStartString :String = ""
    var  mEndString :String = ""
    var mPhoneString :String = ""
    var mMessagerString : String = ""
    lateinit var mFirebselibClass: MfiebaselibsClass
    lateinit var mSpinner2: TextView
    val mAppNames = arrayOf("0","1","2","3")
     var oldFile: File? = null
    var date: String = ""
    var item: String = ""
    var size: String= ""
    var price: String = ""
    var name: String = ""
    private val filePath: String? = null
    lateinit var  mSizeLay :LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass = MfiebaselibsClass(this, this@BigMannerDetailActivity)

        setContentView(R.layout.activity_manner_big_detail)
        phone = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(phone)
        var  s = intent.extras.getString("data")
        var  gson = Gson();
        var itemData :ItemData = gson.fromJson(s,ItemData::class.java)
        date = itemData.date
        name = itemData.name
        item = itemData.item
        price = itemData.price
        size = itemData.size


        initLayout()
    }

    fun initLayout() {
        mSizeLay = findViewById(R.id.sizelay);
        mAddressEdt = findViewById(R.id.editText)
        mCheckBtn = findViewById(R.id.checkbtn)
        mStartbtn = findViewById(R.id.startbtn)
        mEndbtn = findViewById(R.id.endbtn)
        mSpinner = findViewById(R.id.spinner)
        mPriceEdt = findViewById(R.id.editText3)
        mPhotoButtton = findViewById(R.id.button4)
        mPhoneEdt = findViewById(R.id.editText4)
        mMessageEdt = findViewById(R.id.editText5)
        mSendBtn = findViewById(R.id.send)
        mCheckBtn.setOnClickListener(this)
        mStartbtn.setOnClickListener(this)
        mEndbtn.setOnClickListener(this)
        mPhotoButtton.setOnClickListener(this)
        mSendBtn.setOnClickListener(this)

        mSpinner2 = findViewById(R.id.spinner2)
        mMessageEdt.setText(name)
        mPriceEdt.setText(price)
        mSpinner.setText(item)
        mSpinner2.setText(size)
    }

    @SuppressLint("NewApi")
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.send -> {

                sendData()
//                test(MySharedPrefernces.getIsToken(this)
//                        ,latitude.toString()
//                        ,longitude.toString(),mSelectType,mStartbtn.text.toString(),
//                        mEndbtn.text.toString(),mMessagerString,mPhoneString,img,mPriceEdt.text.toString())
            }
        }
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    finish()
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }// other 'case' lines to check for other
        // permissions this app might request
    }

    fun sendData(){
        mMessagerString = mMessageEdt.text.toString()
        mPhoneString = mPriceEdt.text.toString()
        if(!mMessagerString.isEmpty()
                &&!mPriceEdt.text.toString().isEmpty()){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("提示")
            builder.setMessage("以輸入全部資訊")
            builder.setPositiveButton("知道了", { dialog, whichButton ->
                update(date,
                    item
                        ,size
                        ,mMessagerString,
                       mPhoneString)
                dialog.dismiss()
                this.finish()
            })
            // create dialog and show it
            val dialog = builder.create()
            dialog.show()


        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("提示")
            builder.setMessage("請檢查是否有漏內容")
            builder.setPositiveButton("知道了", { dialog, whichButton ->
                dialog.dismiss()
            })
            // create dialog and show it
            val dialog = builder.create()
            dialog.show()
        }



    }
    fun update(date :String,item:String, size:String,name:String, price:String){

        val mCal = Calendar.getInstance()
        val s = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());
        var mHasMap = HashMap<String, String>()
        var key = MySharedPrefernces.getId(this) + s
        mHasMap.put(ResponseData.KEY_DATE,date)
        mHasMap.put(ResponseData.KEY_SIZE,size)
        mHasMap.put(ResponseData.KEY_ITEM,item)
        mHasMap.put(ResponseData.KEY_NAME,name)
        mHasMap.put(ResponseData.KEY_PRICE,price)
        mFirebselibClass.setFireBaseDB(ResponseData.KEY_URL+"/"+"0",date,mHasMap)


    }

//    fun test(id :String,lat :String,lon :String,type:String,start:String,end:String,message:String,
//                phone:String,url :String,price:String){
//        val mCal = Calendar.getInstance()
//        val s = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());
//        var mHasMap = HashMap<String, String>()
//        var key = MySharedPrefernces.getIsToken(this) + s
//        mHasMap.put(ResponseData.KEY_DATE,key)
//        mHasMap.put(ResponseData.KEY_ID,"poPnzPS2cRQv5jxftqnIWWOB9IO2")
//        mHasMap.put(ResponseData.KEY_LAT,lat)
//        mHasMap.put(ResponseData.KEY_LON,lon)
//        mHasMap.put(ResponseData.KEY_SELECT_TYPE,"室內")
//        mHasMap.put(ResponseData.KEY_START_TIME,"0 : 48")
//        mHasMap.put(ResponseData.KEY_END_TIME,"6 : 48")
//        mHasMap.put(ResponseData.KEY_PHONE,"0987987987")
//        mHasMap.put(ResponseData.KEY_MESSAGE,"1111")
//        mHasMap.put(ResponseData.KEY_PRICE,"100")
//        mHasMap.put(ResponseData.KEY_PHOTO_URL,url)
//        mFirebselibClass.setFireBaseDB(ResponseData.KEY_URL+"/"+mType,key,mHasMap)
//
//
//    }

}
