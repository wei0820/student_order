package com.student.student_order

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.*
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import java.io.File
import java.util.*


class BigMannerActivity : AppCompatActivity(), View.OnClickListener, MfirebaeCallback {
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
    lateinit var bitmap: Bitmap
    lateinit var phone: DisplayMetrics
    var img: String = ""
    lateinit var mAddressEdt: EditText
    lateinit var mCheckBtn: Button
    lateinit var mSpinner: Spinner
    lateinit var mStartbtn: Button
    lateinit var mEndbtn: Button
    lateinit var mPriceEdt: EditText
    lateinit var mPhotoButtton: Button
    lateinit var mPhoneEdt: EditText
    lateinit var mMessageEdt: EditText
    lateinit var mSendBtn: Button
     var latitude  :Double = 0.0
     var longitude :Double =0.0
    var mSelectType : String = ""
    var mType : Int = 0

    var mStartString :String = ""
    var  mEndString :String = ""
    var mPhoneString :String = ""
    var mMessagerString : String = ""
    lateinit var mFirebselibClass: MfiebaselibsClass
    lateinit var mSpinner2: Spinner
    val mAppNames = arrayOf(0,1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28)
     var oldFile: File? = null
    private val filePath: String? = null
    lateinit var  mSizeLay :LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass = MfiebaselibsClass(this, this@BigMannerActivity)

        setContentView(R.layout.activity_manner_big)
        phone = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(phone)

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
        val searchSortSpinnerData = arrayOf("漢堡","三明治","蛋餅","飲料")

        val adapter = ArrayAdapter(
                this, // Context
                android.R.layout.simple_spinner_item, // Layout
                searchSortSpinnerData // Array
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        mSpinner.adapter = adapter;

        // Set an on item selected listener for spinner object
        mSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(this@BigMannerActivity, "選擇" + searchSortSpinnerData[position], Toast.LENGTH_SHORT).show()
                mSelectType  = searchSortSpinnerData[position]
                if (position == 3){
                    mSizeLay.visibility = View.VISIBLE
                }else{
                    mSizeLay.visibility = View.GONE

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        mCheckBtn.setOnClickListener(this)
        mStartbtn.setOnClickListener(this)
        mEndbtn.setOnClickListener(this)
        mPhotoButtton.setOnClickListener(this)
        mSendBtn.setOnClickListener(this)
        val searchSortSpinnerData2 =arrayOf(
               "小","中","大")

        mSpinner2 = findViewById(R.id.spinner2)
        mSizeLay.visibility = View.GONE
        val adapter2 = ArrayAdapter(
                this, // Context
                android.R.layout.simple_spinner_item, // Layout
                searchSortSpinnerData2 // Array
        )
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        mSpinner2.adapter = adapter2;
        mSpinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                mType = mAppNames[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }

    @SuppressLint("NewApi")
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.checkbtn -> {
                if (!mAddressEdt.text.isEmpty()) {
                    checkAddress(mAddressEdt.text.toString())
                } else {
                    Toast.makeText(this, "請勿輸入空白", Toast.LENGTH_SHORT).show()
                    return
                }

            }
            R.id.startbtn -> {
                clickTimePicker(mStartbtn)
            }
            R.id.endbtn -> {
            clickTimePicker(mEndbtn)

            }
            R.id.button4 -> {
                selectPic()
            }
            R.id.send -> {

                sendData()
//                test(MySharedPrefernces.getIsToken(this)
//                        ,latitude.toString()
//                        ,longitude.toString(),mSelectType,mStartbtn.text.toString(),
//                        mEndbtn.text.toString(),mMessagerString,mPhoneString,img,mPriceEdt.text.toString())
            }
        }
    }

    fun checkAddress(addrss: String) {
        var geoCoder = Geocoder(this, Locale.getDefault())
        var addressLocation = geoCoder.getFromLocationName(addrss, 1)
        if (addressLocation.size != 0) {
             latitude = addressLocation[0].latitude
            longitude = addressLocation[0].longitude
            Log.d("latitude", latitude.toString())
            Log.d("longitude", longitude.toString())
            val builder = AlertDialog.Builder(this)
            builder.setTitle("取得成功")
            builder.setMessage("latitude:" + latitude.toString() + "\n\n"
                    + "longitude:" + longitude.toString()
            )
            builder.setPositiveButton("知道了", { dialog, whichButton ->
                dialog.dismiss()
            })
            // create dialog and show it
            val dialog = builder.create()
            dialog.show()
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("取得失敗")
            builder.setMessage("請重新檢查地址並重新輸入!!")
            builder.setPositiveButton("知道了", { dialog, whichButton ->
                dialog.dismiss()
            })
            // create dialog and show it
            val dialog = builder.create()
            dialog.show()
        }


    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickTimePicker(button: Button){
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener(function = { view, h, m ->
            button.setText(h.toString() + " : " + m)
        }), hour, minute, false)

        tpd.show()
    }


    private fun selectPic() {
        val permission = ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            //未取得權限，向使用者要求允許權限

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            android.Manifest.permission.CAMERA
                    )
            ) {
                android.support.v7.app.AlertDialog.Builder(this)
                        .setMessage("我真的沒有要做壞事, 給我權限吧?")
                        .setPositiveButton("OK") { dialog, which ->
                            ActivityCompat.requestPermissions(
                                    this,
                                    arrayOf(android.Manifest.permission.CAMERA),
                                    REQUEST_EXTERNAL_STORAGE
                            )
                        }
                        .setNegativeButton("No") { dialog, which -> finish() }
                        .show()
            } else {
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_EXTERNAL_STORAGE)
            }

        } else {
            //開啟相簿相片集，須由startActivityForResult且帶入requestCode進行呼叫，原因
            //為點選相片後返回程式呼叫onActivityResult
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, PHOTO)


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
        mPhoneString = mPhoneEdt.text.toString()
        mMessagerString = mMessageEdt.text.toString()
        if(latitude!=0.0
                &&longitude!=0.0
                && !mStartbtn.text.toString().isEmpty()
                &&!mEndbtn.text.toString().isEmpty()
                &&!img.isEmpty()
                &&!mPhoneString.isEmpty()
                &&!mMessagerString.isEmpty()
                &&!mSelectType.isEmpty()
                &&!mPriceEdt.text.toString().isEmpty()){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("提示")
            builder.setMessage("以輸入全部資訊")
            builder.setPositiveButton("知道了", { dialog, whichButton ->
//                addData(MySharedPrefernces.getIsToken(this)
//                        ,latitude.toString()
//                        ,longitude.toString(),mSelectType,mStartbtn.text.toString(),
//                        mEndbtn.text.toString(),mMessagerString,mPhoneString,img,mPriceEdt.text.toString())
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
    fun addData(id :String,lat :String,lon :String,type:String,start:String,end:String,message:String,
                phone:String,url :String,price:String){
        val mCal = Calendar.getInstance()
        val s = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());
        var mHasMap = HashMap<String, String>()
        var key = MySharedPrefernces.getId(this) + s
        mHasMap.put(ResponseData.KEY_DATE,key)
        mHasMap.put(ResponseData.KEY_SELECT_TYPE,type)
        mHasMap.put(ResponseData.KEY_MESSAGE,message)
        mHasMap.put(ResponseData.KEY_PRICE,price)
        mFirebselibClass.setFireBaseDB(ResponseData.KEY_URL+"/"+mType,key,mHasMap)


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
