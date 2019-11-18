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
    private String add = "";
    private String add2 = "";
    private String add3 = "";
    private String add4 = "";
    private String add5 = "";
    private String add6 = "";
    private String add7 = "";

    private String name;
    private LinearLayout layout,layout2,layout3;
    MfiebaselibsClass mfiebaselibsClass;
    private EditText editText;
    private TextView price;
    private Integer mPrice;
    private Button mEdtBtn;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> priceList = new ArrayList<>();
    private String title;
    private String newName;
    private Integer newPrice ;
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
        mNameText.setText(title+name);
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
                String s = title + name +add +add2
                        +add3+add4+add5+add6+add7;

                mNameText.setText(s);

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
                String s = title + name +add +add2
                        +add3+add4+add5+add6+add7;

                mNameText.setText(s);
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
                String s = title + name +add +add2
                        +add3+add4+add5+add6+add7;

                mNameText.setText(s);
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

                String s = title + name +add +add2
                        +add3+add4+add5+add6+add7;

                mNameText.setText(s);
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
                String s = title + name +add +add2
                        +add3+add4+add5+add6+add7;

                mNameText.setText(s);

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

                String s = title + name +add +add2
                        +add3+add4+add5+add6+add7;

                mNameText.setText(s);


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
                String s = title + name +add +add2
                        +add3+add4+add5+add6+add7;

                mNameText.setText(s);
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
                String s = "品項:"+mNameText.getText().toString()+","+"數量:"+editText.getText().toString()+"," +"總價錢:" +String.valueOf(mPrice*Integer.parseInt(editText.getText().toString()));

                if(MySharedPrefernces.getArrayList(getApplicationContext())!=null){
                    arrayList = MySharedPrefernces.getArrayList(getApplicationContext());
                }else {
                    arrayList = new ArrayList<>();
                }

                if(MySharedPrefernces.getPriceArrayList(getApplicationContext())!=null){
                    priceList = MySharedPrefernces.getPriceArrayList(getApplicationContext());
                }else {
                    priceList = new ArrayList<>();
                }
                arrayList.add(s);
                priceList.add(String.valueOf(mPrice*Integer.parseInt(editText.getText().toString())));
                MySharedPrefernces.saveArrayList(getApplicationContext(),arrayList);
                MySharedPrefernces.savePriceArrayList(getApplicationContext(),priceList);

                Toast.makeText(getApplicationContext(),"已送至購物車",Toast.LENGTH_SHORT).show();


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
          if(getIntent().getStringExtra("title")!=null){
            title = getIntent().getStringExtra("title");
        }else {
              title = "";
          }
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
//        if (getIntent().getStringExtra("newname")!=null){
//            newName = getIntent().getStringExtra("newname");
//            Log.d(TAG, "getData: "+newName);
//        }
//        if (getIntent().getStringExtra("newprice")!=null){
//
//            newPrice = Integer.parseInt(getIntent().getStringExtra("newprice"));
//            Log.d(TAG, "getData: "+newPrice);
//
//        }

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
