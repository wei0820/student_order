package com.student.student_order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class UiHelper {
    public static void setDilog(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("店家資訊")//設定視窗標題
                .setIcon(R.mipmap.appicon)//設定對話視窗圖示
                .setMessage("地址：苗栗縣頭份市珊瑚里學府路86號" + "\n\n" + "電話：037603156")//設定顯示的文字
                .setPositiveButton("關閉視窗", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })//設定結束的子視窗
                .show();//呈現對話視窗
    }

    public static void setShopDilog(Context context) {
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle("訂單")//設定視窗標題
                .setMessage("請選擇修改或刪除")//設定顯示的文字
                .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })//設定結束的子視窗
                .setNegativeButton("刪除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();//呈現對話視窗
    }

    public static void setEditDilog(Context context, final ArrayList<String> arrayList, final ArrayAdapter<String> arrayAdapter) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("修改數量");
        alertDialog.setMessage("請輸入數量");

        final EditText input = new EditText(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
        alertDialog.setView(input);
        alertDialog.setPositiveButton("確定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Jack","in");
                        Log.d("Jack", arrayList.toString());

                       String s =  arrayList.toString().replace("[","");
                        String a = s.replace("]","");
                        String[]arr = a.split(",");
                         String name = arr[0];
                        Log.d("Jack",arr[1]+"");
                     int n = Integer.parseInt(arr[1]);


                        String s1 = name+","+n;
                        arrayList.add(s1);
                        arrayAdapter.notifyDataSetChanged();
                        dialog.dismiss();



                    }
                });

//        alertDialog.setNegativeButton("NO",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });

        alertDialog.show();
    }
    public static void setShopCarDilog(Context context,String s) {
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle("訂單資訊")//設定視窗標題
                .setMessage(s)//設定顯示的文字
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })//設定結束的子視窗
                .show();//呈現對話視窗
    }
}

