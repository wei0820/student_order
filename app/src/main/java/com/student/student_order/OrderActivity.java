package com.student.student_order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class OrderActivity extends AppCompatActivity {
    private ListView mListview,mListview2;
    private static final String TAG = "OrderActivity";




    String[] parents = new String[]{
            "漢堡","三明治","蛋餅","飲料"};
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        mListview = findViewById(R.id.listview);
        mListview2 = findViewById(R.id.listview2);
        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 ,parents);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(OrderActivity.this,"您選擇了"+parents[i].toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("menu",i);
                bundle.putString("name",parents[i]);
                bundle.putString("type","order");
                intent.putExtras(bundle);
                intent.setClass(getApplication(),Order_1Activity.class);
                startActivity(intent);
            }
        });
    }
}
