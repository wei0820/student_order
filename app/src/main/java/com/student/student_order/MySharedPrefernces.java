package com.student.student_order;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
public class MySharedPrefernces {
    public static final String NAME = "MySharedPrefernces";


    //首頁-是否第一次使用
    public static final String KEY_IS_BUY = "isBuy";
    public static void saveIsBuyed(Context context, boolean isBuyed) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_IS_BUY, isBuyed).apply();
    }

    public static boolean getIsBuyed(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getBoolean(KEY_IS_BUY, false);
    }


    // 儲存
    public static final String KEY_ID = "id";

    public static void saveId(Context context, int userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putInt(KEY_ID, userid).commit();


    }

    public static int getId(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getInt(KEY_ID, 0);
    }

    // 儲存 userid
    public static final String KEY_USERID = "userid";

    public static void saveUserId(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_USERID, userid).commit();


    }

    public static String getUserId(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_USERID, "");
    }


    // 儲存 userid
    public static final String KEY_MUSIC = "music";

    public static void saveMusicState(Context context, int userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putInt(KEY_MUSIC, userid).commit();


    }

    public static int getMusicState(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getInt(KEY_MUSIC, 0);
    }

    // 儲存 userpic
    public static final String KEY_USERPIC = "userpic";

    public static void saveUserPic(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_USERPIC, userid).commit();


    }

    public static String getUserPic(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_USERPIC, "");
    }

    // 儲存 username
    public static final String KEY_USERNAME = "username";

    public static void saveUserName(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_USERNAME, userid).commit();


    }

    public static String getUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_USERNAME, "");
    }

    // 儲存 useremail
    public static final String KEY_USERMAIL = "usermail";

    public static void saveUserMail(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_USERMAIL, userid).commit();


    }

    public static String getUserMail(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_USERMAIL, "");
    }
    // 儲存 useremail
    public static final String KEY_USERTALL = "usertall";

    public static void saveUserTall(Context context, int userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putInt(KEY_USERTALL, userid).commit();


    }

    public static int getUserTall(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getInt(KEY_USERTALL, 0);
    }


    // 儲存 useremail
    public static final String KEY_WEIGHT = "userweight";

    public static void saveUserWeight(Context context, int userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putInt(KEY_WEIGHT, userid).commit();


    }

    public static int getUserWeight(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getInt(KEY_WEIGHT,0);
    }


    // 儲存 useremail
    public static final String KEY_SEX = "usersex";

    public static void saveUserSex(Context context, int userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putInt(KEY_SEX, userid).commit();


    }

    public static int getUserSex(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getInt(KEY_SEX,0);
    }





    // 儲存 useremail
    public static final String KEY_STEP = "userstep";

    public static void saveUserStep(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_STEP, userid).commit();


    }

    public static String getUserStep(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_STEP,"");
    }


    // 儲存 useremail
    public static final String KEY_KM = "userkm";

    public static void saveUserKm(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_KM, userid).commit();


    }

    public static String getUserKm(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_KM,"");
    }



    public static final String KEY_DHOT = "userdhot";

    public static void saveUserDhot(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_DHOT, userid).commit();


    }

    public static String getUserDhot(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_DHOT,"");
    }



    public static final String KEY_HOT = "userhot";

    public static void saveUserhot(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_HOT, userid).commit();


    }

    public static String getUserhot(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_HOT,"");
    }

    public static final String KEY_PHOTO = "userphoto";

    public static void saveUserPhoto(Context context, String userid) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_PHOTO, userid).commit();


    }

    public static String getUserphoto(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_PHOTO,"");
    }


    // 	// 通知中心 時間記錄
    public  static  final  String KEY_MYCARD_TIME = "mycardtime";
    public static  void saveMyCardTime (Context context ,String time){
        SharedPreferences sp = context.getSharedPreferences(NAME,Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_MYCARD_TIME, time + "").commit();


    }
    public static String getMyCardTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_MYCARD_TIME, "");
    }



    // 	// 通知中心 時間記錄
    public  static  final  String KEY_MYCARD_TIME_2 = "mycardtime2";
    public static  void saveMyCardTime2(Context context ,String time){
        SharedPreferences sp = context.getSharedPreferences(NAME,Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_MYCARD_TIME_2, time + "").commit();


    }
    public static String getMyCardTime2(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_MYCARD_TIME_2, "");
    }
}
