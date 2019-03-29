package com.student.student_order

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import org.json.JSONException
import org.json.JSONObject
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class FacebookManager {
    private var profileTracker: ProfileTracker? = null
    private var auth: FirebaseAuth? = null
    private val authListener: FirebaseAuth.AuthStateListener? = null
    private var accessTokenTracker: AccessTokenTracker? = null

    //臉書登入
    fun fbLogin(context: Context, mFbLoginButton: LoginButton, callbackManager: CallbackManager) {
        val PERMISSIONS_PUBLISH =
            Arrays.asList("public_profile", "email", "user_friends", "user_location", "user_birthday", "user_likes")
        mFbLoginButton.setReadPermissions(PERMISSIONS_PUBLISH)
        mFbLoginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                //                handleFacebookAccessToken(context,loginResult.getAccessToken());
                //                setUsetProfile();
                val request = GraphRequest.newMeRequest(
                    loginResult.accessToken
                ) { `object`, response ->
                    // Get facebook data from login
                    val bFacebookData = getFacebookData(context, `object`)
                    //                        LoginManager.getInstance().logOut();
                    (context as Activity).finish()
                }
                val parameters = Bundle()
                parameters.putString(
                    "fields",
                    "id, first_name, last_name, email,gender, birthday, location"
                ) // Parámetros que pedimos a facebook
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {
                error.printStackTrace()


            }

        })


    }

    fun handleFacebookAccessToken(context: Context, token: AccessToken) {


        accessTokenTracker = object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken, newAccessToken: AccessToken) {
                //                updateWithToken(newAccessToken);
            }
        }

        auth = FirebaseAuth.getInstance()

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth!!.signInWithCredential(credential)
            .addOnCompleteListener(context as Activity, object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {

                    }
                }
            })
    }

    private fun getFacebookData(context: Context, `object`: JSONObject): Bundle? {

        try {
            val bundle = Bundle()
            val id = `object`.getString("id")
            var photo = ""
//            MySharedPrefernces.saveUserLoginState(context, 1)
//            MySharedPrefernces.saveUserId(context, id)

            try {
                val profile_pic = URL("https://graph.facebook.com/$id/picture?width=200&height=150")
                bundle.putString("profile_pic", profile_pic.toString())
                photo = profile_pic.toString()

            } catch (e: MalformedURLException) {
                e.printStackTrace()
                return null
            }

            bundle.putString("idFacebook", id)
            if (`object`.has("first_name"))
                bundle.putString("first_name", `object`.getString("first_name"))
            val firstName = `object`.getString("first_name")
            if (`object`.has("last_name"))
                bundle.putString("last_name", `object`.getString("last_name"))
            val lastName = `object`.getString("last_name")
            if (`object`.has("email"))
                bundle.putString("email", `object`.getString("email"))
            val mail = `object`.getString("email")
            if (`object`.has("gender"))
                bundle.putString("gender", `object`.getString("gender"))
            val gender = `object`.getString("gender")
            if (`object`.has("birthday"))
                bundle.putString("birthday", `object`.getString("birthday"))
            val birthday = `object`.getString("birthday")
            //            MyApi.DateComparison(System.currentTimeMillis(),System.currentTimeMillis());

            if (`object`.has("location"))
                bundle.putString("location", `object`.getJSONObject("location").getString("name"))
            val location = `object`.getJSONObject("location").getString("name")
            //            setMemberData(id, firstName, lastName, mail, String.valueOf(MyApi.birthdayToTimeStamp(object.getString("birthday"))), gender, "", photo, location);
            return bundle
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }

    fun setUsetProfile(
        context: Context,
        mFbImageView: ImageView,
        mUserIdTextView: TextView,
        mUserAccountTextView: TextView
    ) {
        profileTracker = object : ProfileTracker() {
            protected override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
                if (oldProfile != null) {
                    //登出後
                    //                    fbName.setText("");
                    mFbImageView.setImageBitmap(null)

                }

                if (currentProfile != null) {
                    //登入
                    //                    fbName.setText(currentProfile.getName());
//                    MySharedPrefernces.saveUserPhoto(
//                        context,
//                        String.valueOf(currentProfile.getProfilePictureUri(150, 150))
//                    )
//                    MyApi.loadImage(
//                        String.valueOf(currentProfile.getProfilePictureUri(150, 150)),
//                        mFbImageView,
//                        context
//                    )
                    val id = currentProfile.id
                    val name = currentProfile.name
                    mUserIdTextView.text = id
                    mUserAccountTextView.text = name

                }

            }
        }
        (profileTracker as ProfileTracker).startTracking()
        if ((profileTracker as ProfileTracker).isTracking()) {
            if (Profile.getCurrentProfile() == null) return
            if (Profile.getCurrentProfile().getProfilePictureUri(150, 150) != null) {
//                MyApi.loadImage(
//                    String.valueOf(Profile.getCurrentProfile().getProfilePictureUri(150, 150)),
//                    mFbImageView,
//                    context
//                )
            }

        }
    }
    fun checkFbState(){
        if(Profile.getCurrentProfile()!=null){
            val profile = Profile.getCurrentProfile()
            val userPhoto = profile.getProfilePictureUri(300, 300)
            val id = profile.id
            val name = profile.name

        }else{


        }
    }
}