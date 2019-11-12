package com.student.student_order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class FastAddActivity extends AppCompatActivity {
    private ListView mListView;
    ListAdapter adapter;
        String[] parents = new String[]{"蘑菇豬排 65",
                "黑胡椒牛肉 65" ,
                "咖哩雞肉 65",
                "義大利肉醬 75" ,
                "德國香腸 75" ,
                "義式奶青 75",
                "厚切培根牛肉堡 90"};
    int[] price = new int[]{65, 65 , 65, 75 , 75 ,75,90};
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
                bundle.putInt("price",price[i]);
                bundle.putString("type","fast");
                intent.putExtras(bundle);
                intent.setClass(getApplication(),AddMenuActivity.class);
                startActivity(intent);
            }
        });

    }
}
