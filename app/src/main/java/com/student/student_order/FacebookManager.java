package com.student.student_order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;


import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class FacebookManager {
    private static final String TAG = "FacebookManager";
    private static ProfileTracker profileTracker;
    private static FirebaseAuth auth;
    private static FirebaseAuth.AuthStateListener authListener;
    private static AccessTokenTracker accessTokenTracker;

    //臉書登入
    public static void fbLogin(final Context context, LoginButton mFbLoginButton, CallbackManager callbackManager, final Class<?> c) {
        List<String> PERMISSIONS_PUBLISH = Arrays.asList("public_profile", "email");
        mFbLoginButton.setReadPermissions(PERMISSIONS_PUBLISH);
        mFbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,loginResult.getAccessToken().getToken());
                handleFacebookAccessToken(context,loginResult.getAccessToken());
//                setUsetProfile();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d(TAG, object.toString());
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(context,object);
                        ((Activity)context).startActivity(new Intent( ((Activity)context),c));
                        ((Activity)context).finish();

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.d(TAG,"onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();
                Log.d(TAG,error.toString());


            }

        });


    }

    public static void handleFacebookAccessToken(Context context,AccessToken token) {


        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
//                updateWithToken(newAccessToken);
            }
        };

        // [START_EXCLUDE silent]

        // [END_EXCLUDE]
        auth = FirebaseAuth.getInstance();

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());

                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
    }

    public static  void checkFbState(Context context ,ImageView mFbImageView, TextView mUserIdTextView,
                                     TextView mUserAccountTextView) {
        if (Profile.getCurrentProfile() != null) {
            Profile profile = Profile.getCurrentProfile();
            // 取得用戶大頭照
            Uri userPhoto = profile.getProfilePictureUri(300, 300);
            String id = profile.getId();
            String name = profile.getName();
            mUserAccountTextView.setText("會員姓名:"+name);
            mUserIdTextView.setText("會員編號:"+id);
            Picasso.get()
                    .load(userPhoto)
                    .resize(150, 150)
                    .centerCrop()
                    .into(mFbImageView);



        } else {
            mFbImageView.setImageDrawable(null);
            mUserAccountTextView.setText("");
            mUserIdTextView.setText("");

        }

    }
    public static  String checkFbStateString(Context context) {
        if (Profile.getCurrentProfile() != null) {
            Profile profile = Profile.getCurrentProfile();
            // 取得用戶大頭照
            Uri userPhoto = profile.getProfilePictureUri(300, 300);
            String id = profile.getId();
            String name = profile.getName();
            return id+","+name+","+userPhoto;

        } else {

            return  "";
        }


    }

    public static  boolean checkFbState(Context context) {
        if (Profile.getCurrentProfile() != null) {
            Profile profile = Profile.getCurrentProfile();
            // 取得用戶大頭照
            Uri userPhoto = profile.getProfilePictureUri(300, 300);
            String id = profile.getId();
            String name = profile.getName();
            return true;

        } else {

            return  false;
        }


    }

    public static  void setUsetProfile(final Context context , final ImageView mFbImageView, final TextView mUserIdTextView, final TextView mUserAccountTextView) {
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (oldProfile != null) {
                    //登出後
                    mFbImageView.setImageBitmap(null);

                }

                if (currentProfile != null) {
                    //登入
                    String id = currentProfile.getId();
                    String name = currentProfile.getName();
                    mUserIdTextView.setText(id);
                    mUserAccountTextView.setText(name);

                }

            }
        };
        profileTracker.startTracking();
        if (profileTracker.isTracking()) {
            if (Profile.getCurrentProfile() == null) return;
            if (Profile.getCurrentProfile().getProfilePictureUri(150, 150) != null) {


            }

        }
    }

    private static Bundle getFacebookData(Context context,JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");
            String photo = "";
            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.d(TAG, profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
                photo = profile_pic.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            String firstName = object.getString("first_name");
            Log.d(TAG, "getFacebookData: " + object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            String lastName = object.getString("last_name");
            Log.d(TAG, "getFacebookData: " + object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            Log.d(TAG, "getFacebookData: " + object.getString("email"));
            String mail = object.getString("email");
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            String gender = object.getString("gender");
            Log.d(TAG, "getFacebookData: " + object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            String birthday = object.getString("birthday");
//            MyApi.DateComparison(System.currentTimeMillis(),System.currentTimeMillis());

            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));
            Log.d(TAG, "getFacebookData: " + object.getJSONObject("location").getString("name"));
            String location = object.getJSONObject("location").getString("name");
//            setMemberData(id, firstName, lastName, mail, String.valueOf(MyApi.birthdayToTimeStamp(object.getString("birthday"))), gender, "", photo, location);
            return bundle;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info =pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i(TAG, "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }
    }

    public static  void checkFbState(Context context ,ImageView mFbImageView, TextView mUserIdTextView,
                                     TextView mUserAccountTextView,TextView mEmailText) {
        if (Profile.getCurrentProfile() != null) {
            Profile profile = Profile.getCurrentProfile();
            // 取得用戶大頭照
            Uri userPhoto = profile.getProfilePictureUri(300, 300);
            String id = profile.getId();
            String name = profile.getName();
            mUserAccountTextView.setText(name);
            mUserIdTextView.setText(id);
            Picasso.get()
                    .load(userPhoto)
                    .resize(150, 150)
                    .centerCrop()
                    .into(mFbImageView);



        } else {
            mFbImageView.setImageDrawable(null);
            mUserAccountTextView.setText("");
            mUserIdTextView.setText("");

        }

    }
}