package com.student.student_order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class UiHelper {
    public static  void setDilog(Context context){
        new AlertDialog.Builder(context)
                .setTitle("店家資訊")//設定視窗標題
                .setIcon(R.mipmap.appicon)//設定對話視窗圖示
                .setMessage("地址：苗栗縣頭份市珊瑚里學府路86號"+"\n\n"+"電話：037603156")//設定顯示的文字
                .setPositiveButton("關閉視窗",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })//設定結束的子視窗
                .show();//呈現對話視窗
    }
}


