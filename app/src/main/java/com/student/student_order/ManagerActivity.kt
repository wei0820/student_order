package com.student.student_order

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback

class ManagerActivity : AppCompatActivity() , MfirebaeCallback {
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
        if (p0!=null){
            val gson = Gson()
            val jsonInString = gson.toJson(p0)
            val itemData :ItemData = gson.fromJson(jsonInString,ItemData::class.java)
            array.add(itemData)

            arrayList.add(itemData.name)
        }
        food!!.notifyDataSetChanged()

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
    lateinit var mFirebselibClass: MfiebaselibsClass
    lateinit var mProgressDialog : ProgressDialog
    lateinit var mListView: ListView
    private var food : ArrayAdapter<String>? =null
    var arrayList =ArrayList<String>()
    var array = ArrayList<ItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass = MfiebaselibsClass(this, this)
        setContentView(R.layout.activity_manager)
        getData()

        mListView = findViewById(R.id.list)


        food =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        mListView.adapter = food
        mListView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent()
            val bundle = Bundle()
            val gson = Gson()

            bundle.putString("data",gson.toJson(array[i]))
            intent.putExtras(bundle)
            intent.setClass(this,BigMannerDetailActivity::class.java)
            startActivity(intent)


        }
    }

    fun  getData(){
        val s = intent.extras.getString("type")
        Log.d("Jak",s)
        mFirebselibClass.getFirebaseDatabase(ResponseData.KEY_URL+s,"date")

        }
}
