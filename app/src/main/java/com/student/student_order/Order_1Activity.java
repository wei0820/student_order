package com.student.student_order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Order_1Activity extends AppCompatActivity {
    private ListView mListView;
    private String name = "";
    private int lay = 0;
    String type = "";
    String[] menu1 = new String[]{"煎蛋","起司蛋","火腿蛋","豬肉蛋","培根蛋","肉鬆蛋","雞肉蛋","薯餅蛋","鮪魚蛋","牛肉蛋","鱈魚","黑胡椒豬排","煙燻雞","無骨雞排","起司豬排","卡啦雞腿","椒麻雞腿","泰式雞腿","熱狗薯餅蛋","豬肉總匯","薯餅總匯","牛肉總匯","豬肉總匯","卡啦雞總匯"};
    String[] menu2 =new String[]{"煎蛋","起司蛋","火腿蛋","豬肉蛋","培根蛋","肉鬆蛋","雞肉蛋","薯餅蛋","鮪魚蛋","牛肉蛋","鱈魚","黑胡椒豬排","煙燻雞肉","無骨雞排","起司豬排","卡啦雞腿","椒麻雞腿","泰式雞腿"};
    String[] menu3 =new String[]{"原味","玉米","乳酪絲","起司","火腿","肉鬆","培根","蔬菜","熱狗","鮪魚","豬肉 ","雞肉","薯餅","豬排","牛肉","燻雞","德國香腸","卡啦"};
    String[] menu4 =new String[]{"原味","玉米 ","乳酪絲","起司","火腿","肉鬆","培根","蔬菜","熱狗","鮪魚","豬肉","雞肉","薯餅","豬排","牛肉","燻雞","德國香腸","卡啦"};
    String[] menu5 =new String[]{"薯餅","薯餅塔","小熱狗","大熱狗","小雞塊","大雞塊","薯條","卡啦","椒麻"};
    String[] menu6 = new String[]{"花生 薄片","花生 厚片","草莓 薄片","草莓 厚片",
            "巧克力 薄片","巧克力 厚片","椰香 薄片","椰香 厚片",
            "香蒜 薄片","香蒜 厚片","煙燻起司 薄片","煙燻起司 厚片",
            "綠茶 薄片","綠茶 厚片","奶油 薄片","奶油 厚片",
            "藍莓 薄片","藍莓 厚片"};
    String[] menu7 = new String[]{"蘿蔔糕","煎餃","鍋貼","蔥抓餅","翡翠抓餅"};
    String[] menu8 = new String[]{"蘑菇豬排","黑胡椒牛肉","咖喱雞肉","義大利肉醬 德國香腸"};
    String[] menu9 = new String[]{"豆漿 大 ","豆漿 小 ","豆紅茶 大 ","豆紅茶 小"
            ,"紅茶 大","紅茶 中 ","紅茶 小 ","奶茶 大","奶茶 中 ","奶茶 小 ","檸檬紅茶 大","檸檬紅茶 小 ",
            "蘋果紅茶 大 ","蘋果紅茶 小 ","柳橙汁 大 ","柳橙汁 中 ","柳橙汁 小 ",
            "檸檬汁 大 ","檸檬汁 中","檸檬汁 小", "蘋果汁 大 ","蘋果汁 中","蘋果汁 小 ", "可樂 ","雪碧","鮮奶茶 大","鮮奶茶 小","玉米濃湯 大","玉米濃湯 小"};
    String[] menu10 = new String[]{"蘑菇","黑胡椒","肉燥","咖哩","韓國肉醬","義大利肉醬","奶油白醬 ","三杯雞","宮保雞丁","川味麻婆 "};

    Integer [] i2 =new Integer[]{25,30,30,30,30,30,30,30,30,35,35,35,35,35,45,45,45,45};
    Integer [] i1 =new Integer[]{20,25,25,30,25,25,30,30,30,35,35,35,35,35,45,45,45,45,40,40,45,45,50,55};
    Integer [] i4 =new Integer[]{20,25,25,25, 25,25,25,25,25,30,30,30,30,35,35,35,35,45};
    Integer [] i9 =new Integer[]{15,10,15,10,20,15,10,20,15,10,20,15,20,15,20,15,10,20,15,10,20,15,10,20,20,25,20,30,20};

    ListAdapter adapter;
    private  String[] mString = new String[]{};
    private  Integer[] price = new Integer[]{};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_1);
        mListView = findViewById(R.id.listview);
        getData();

        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 ,mString);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Order_1Activity.this,"您選擇了"+mString[i].toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("menu",lay);
                bundle.putString("name",mString[i]);
                bundle.putInt("price",price[i]);
                bundle.putString("type",type);
                intent.putExtras(bundle);
                intent.setClass(getApplication(),AddMenuActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private void  getData(){
        lay =  getIntent().getIntExtra("menu",0);
       name = getIntent().getStringExtra("name");
      type  = getIntent().getStringExtra("type");
       if(type.equals("order")){
           switch (lay){
               case 0:
                   mString = menu2;
                   price = i2;
                   break;
               case 1:
                   mString = menu1;
                   price = i1;
                   break;
               case 2:
                   mString = menu4;
                   price = i4;
                   break;
               case 3:
                   mString = menu9;
                   price = i9;
                   break;

           }

       }else {
           switch (lay){
               case 0:
                   mString = menu1;
                   break;
               case 1:
                   mString = menu2;

                   break;
               case 2:
                   mString = menu3;

                   break;
               case 3:
                   mString = menu4;

                   break;
               case 4:
                   mString = menu5;

                   break;
               case 5:
                   mString = menu6;

                   break;
               case 6:
                   mString = menu7;

                   break;
               case 7:
                   mString = menu8;

                   break;
               case 8:
                   mString = menu9;

                   break;
               case 9:
                   mString = menu10;

                   break;


           }
       }



    }
}
