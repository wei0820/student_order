package com.student.student_order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class FastAddActivity extends AppCompatActivity {
    private ListView mListView;
    ListAdapter adapter;
        String[] parents = new String[]{"三明治",
            "漢堡","蛋餅","河粉","點心","吐司類","中式類","套餐類","飲料類","鐵板類"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_add);
        mListView = findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 ,parents);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(FastAddActivity.this,"您選擇了"+parents[i].toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("menu",i);
                bundle.putString("name",parents[i]);
                bundle.putString("type","fast");
                intent.putExtras(bundle);
                intent.setClass(getApplication(),Order_1Activity.class);
                startActivity(intent);
            }
        });

    }
}
