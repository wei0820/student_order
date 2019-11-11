package com.student.student_order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass;
import com.jackpan.libs.mfirebaselib.MfirebaeCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AddMenuActivity extends AppCompatActivity implements MfirebaeCallback {
    private SwitchCompat switchCompat1,switchCompat2,switchCompat3,switchCompat4,switchCompat5,switchCompat6,switchCompat7;
    private TextView mNameText;
    private Button mButton1,mButton2;
    private String add,add2,add3,add4,add5,add6,add7;
    private String name;
    private LinearLayout layout,layout2,layout3;
    MfiebaselibsClass mfiebaselibsClass;
    private EditText editText;
    private TextView price;
    private Integer mPrice;
    private Button mEdtBtn;
    private ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mfiebaselibsClass = new MfiebaselibsClass(this,this);
        setContentView(R.layout.activity_add_menu);
        layout = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        mNameText = findViewById(R.id.nametext);
        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        switchCompat1 = findViewById(R.id.switch1);
        switchCompat2 = findViewById(R.id.switch2);

        switchCompat3 = findViewById(R.id.switch3);
        switchCompat4 = findViewById(R.id.switch4);
        switchCompat5 = findViewById(R.id.switch5);
        switchCompat6 = findViewById(R.id.switch6);
        switchCompat7 = findViewById(R.id.switch7);

        editText = findViewById(R.id.num);
        price = findViewById(R.id.price);
        mEdtBtn = findViewById(R.id.edtbtn);
        getData();

        switchCompat1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplication(),"要加生菜",Toast.LENGTH_SHORT).show();
                    add = "要加生菜";
                }else {
                    Toast.makeText(getApplication(),"不加生菜",Toast.LENGTH_SHORT).show();
                    add = "不加生菜";

                }

                mNameText.setText(name + add);

            }
        });
        switchCompat2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplication(),"要加洋蔥",Toast.LENGTH_SHORT).show();
                    add2 = "要加洋蔥";
                }else {

                    Toast.makeText(getApplication(),"不加洋蔥",Toast.LENGTH_SHORT).show();
                    add2 = "不加洋蔥";
                }
                mNameText.setText(name + add +add2);

            }
        });
        switchCompat3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplication(),"要加番茄醬",Toast.LENGTH_SHORT).show();
                    add3 = "要加番茄醬";
                }else {
                    Toast.makeText(getApplication(),"不加番茄醬",Toast.LENGTH_SHORT).show();
                    add3 = "不加番茄醬";
                }
                mNameText.setText(name + add +add2 +add3);

            }
        });
        switchCompat4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplication(),"要加醬油膏",Toast.LENGTH_SHORT).show();
                    add4 = "要加醬油膏";
                }else {
                    Toast.makeText(getApplication(),"不加醬油膏",Toast.LENGTH_SHORT).show();
                    add4 = "不加醬油膏";
                }

                mNameText.setText(name + add4);

            }
        });
        switchCompat5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplication(),"要加泰式酸辣醬",Toast.LENGTH_SHORT).show();
                    add5 = "要加泰式酸辣醬";
                }else {
                    Toast.makeText(getApplication(),"不加泰式酸辣醬",Toast.LENGTH_SHORT).show();
                    add5 = "不加泰式酸辣醬";
                }
                if (add4!=null){
                    mNameText.setText(name + add4 +add5 );

                }else {
                    mNameText.setText(name+add5 );

                }

            }
        });
        switchCompat6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if(b){
                    Toast.makeText(getApplication(),"要加番茄醬",Toast.LENGTH_SHORT).show();
                    add6 = "要加番茄醬";
                }else {
                    Toast.makeText(getApplication(),"不加番茄醬",Toast.LENGTH_SHORT).show();
                    add6 = "不加番茄醬";
                }

                if (add4!=null&&add5!=null){
                    mNameText.setText(name + add4 +add5 +add6 );

                }else {
                    mNameText.setText(name+add6);

                }


            }
        });
        switchCompat7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplication(),"要加冰塊",Toast.LENGTH_SHORT).show();
                    add7 = "要加冰塊";
                }else {
                    Toast.makeText(getApplication(),"不加冰塊",Toast.LENGTH_SHORT).show();
                    add7 = "不加冰塊";
                }
                mNameText.setText(name + add7);

            }
        });

        mEdtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText(String.valueOf(mPrice*Integer.parseInt(editText.getText().toString())));
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Jack",mPrice+"");
                String s = mNameText.getText().toString()+","+editText.getText().toString()+","+
                        String.valueOf(mPrice*Integer.parseInt(editText.getText().toString()));

                if(MySharedPrefernces.getArrayList(getApplicationContext())!=null){
                    arrayList = MySharedPrefernces.getArrayList(getApplicationContext());
                }else {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(s);
                MySharedPrefernces.saveArrayList(getApplicationContext(),arrayList);
//                Calendar mCal = CalendaretInstance();
//                CharSequence s = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());
//
//                // kk:24小時制, hh:12小時制
//
//                HashMap<String,String> hashMap = new HashMap<>();
//                hashMap.put("id",MySharedPrefernces.getId(AddMenuActivity.this));
//                hashMap.put("time",System.currentTimeMillis()+"");
//                hashMap.put("food",mNameText.getText().toString());
//                mfiebaselibsClass.setFireBaseDB("https://order-3fe87.firebaseio.com/FavoriteList" + "/" + MySharedPrefernces.getId(AddMenuActivity.this), s.toString(), hashMap);
//                Toast.makeText(AddMenuActivity.this,"送出訂單,請到歷史紀錄觀看",Toast.LENGTH_SHORT).show();
//                finish();

            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private static final String TAG = "AddMenuActivity";
    private void getData() {
        mPrice = getIntent().getIntExtra("price",0);
        name = getIntent().getStringExtra("name");
        int i = getIntent().getIntExtra("menu", 0);
        String type = getIntent().getStringExtra("type");
        if (type.equals("fast")){
            mNameText.setText(name);
            layout.setVisibility(View.GONE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
        }else {
            switch (i) {
                case 2:
                    layout.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    layout3.setVisibility(View.GONE);
                    break;
                case 3:
                    layout.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.VISIBLE);
                    break;
                default:
                    layout.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);
                    break;
            }
        }

    }


    @Override
    public void getDatabaseData(Object o) {


    }

    @Override
    public void getDeleteState(boolean b, String s, Object o) {

    }

    @Override
    public void createUserState(boolean b) {

    }

    @Override
    public void useLognState(boolean b) {

    }

    @Override
    public void getuseLoginId(String s) {

    }

    @Override
    public void getuserLoginEmail(String s) {

    }

    @Override
    public void resetPassWordState(boolean b) {

    }

    @Override
    public void getFireBaseDBState(boolean b, String s) {

    }

    @Override
    public void getFirebaseStorageState(boolean b) {

    }

    @Override
    public void getFirebaseStorageType(String s, String s1) {

    }

    @Override
    public void getsSndPasswordResetEmailState(boolean b) {

    }

    @Override
    public void getUpdateUserName(boolean b) {

    }

    @Override
    public void getUserLogoutState(boolean b) {

    }
}
