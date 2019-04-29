package com.student.student_order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class AddMenuActivity extends AppCompatActivity {
    private SwitchCompat switchCompat1,switchCompat2,switchCompat3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        switchCompat1 = findViewById(R.id.switch1);
        switchCompat2 = findViewById(R.id.switch2);

        switchCompat3 = findViewById(R.id.switch3);
        switchCompat1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplication(),"要加生菜",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplication(),"不加生菜",Toast.LENGTH_SHORT).show();

                }
            }
        });
        switchCompat2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplication(),"要加洋蔥",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplication(),"不加洋蔥",Toast.LENGTH_SHORT).show();

                }
            }
        });
        switchCompat3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplication(),"要加番茄醬",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplication(),"不加番茄醬",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
