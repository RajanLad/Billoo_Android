package fr.billoo.mobile.BaseFunctionalities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.net.InetAddress;
import java.util.HashMap;

import fr.billoo.mobile.HomeActivity;
import fr.billoo.mobile.LoginActivity;

public class SessionManager
{
    SharedPreferences billoooSession;

    private static SessionManager sessionManagerInstance;

    Editor edit;

    Context context;

    int PRIVATE_MODE=0;

    // Sharedpref file name
    private static final String SESSION_NAME = "BOOKARIDESESSION";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = Functions.encrypt("IsLoggedIn");

    //to check if signedup or not
    private static final String IS_SIGNUP = Functions.encrypt("IsSignUp");

    //to check if verified or not
    private static final String IS_VERIFIED = Functions.encrypt("IsVerified");

    // email (make variable public to access from outside)
    public static final String KEY_EMAIL = Functions.encrypt("email");

    // token (make variable public to access from outside)
    public static final String KEY_TOKEN = Functions.encrypt("token");

    // userid (make variable public to access from outside)
    public static final String KEY_USERID = Functions.encrypt("userid");

    // barcode (make variable public to access from outside)
    public static final String KEY_BARCODE = Functions.encrypt("barcode");

    //Error values
    public static final String ERROR_CODE_IN_CASE = Functions.encrypt("ERROR_CODE_IN_CASE");




    // Constructor
    private SessionManager(Context context){
        this.context = context;
        billoooSession = this.context.getSharedPreferences(SESSION_NAME, PRIVATE_MODE);
        edit = billoooSession.edit();

    }

    public static synchronized SessionManager getInstance(Context context) {
        //if (sessionManagerInstance == null) {
            sessionManagerInstance = new SessionManager(context);
        //}
        return sessionManagerInstance;
    }


    /*
    * Store the data after signing up
    * */

    public void createUserDetailsForSignupSession(String email, String token,String userid,String barcode){
        // Storing login value as FALSE because user is not logged in
        edit.putBoolean(IS_LOGIN, false);

        // Storing signup value as TRUE because user is signed up
        edit.putBoolean(IS_SIGNUP, true);

        // Storing verify value as FALSE because user is not verified
        edit.putBoolean(IS_VERIFIED, false);

        // Storing email in pref
        edit.putString(KEY_EMAIL, Functions.encrypt(email));

        // Storing token in pref
        edit.putString(KEY_TOKEN, Functions.encrypt(token));

        // Storing userid in pref
        edit.putString(KEY_USERID, Functions.encrypt(userid));

        // Storing barcode in pref
        edit.putString(KEY_BARCODE, Functions.encrypt(barcode));

        // commit changes
        edit.apply();
    }

    /*
    * Update the user details
    * */
    public void updateUserDetailsAfterLogin(String email, String token,String userid,String barcode){

        edit = billoooSession.edit();

        // Storing login value as FALSE because user is not logged in
        edit.putBoolean(IS_LOGIN, true);

        // Storing verify value as FALSE because user is not verified
        edit.putBoolean(IS_VERIFIED, true);

        // Storing email in pref
        edit.putString(KEY_EMAIL, Functions.encrypt(email));

        // Storing token in pref
        edit.putString(KEY_TOKEN, Functions.encrypt(token));

        // Storing userid in pref
        edit.putString(KEY_USERID, Functions.encrypt(userid));

        // Storing barcode in pref
        edit.putString(KEY_BARCODE, Functions.encrypt(barcode));

        // commit changes
        edit.apply();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String mobile){
        // Storing login value as TRUE
        edit.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        edit.putString(KEY_EMAIL, name);

        // Storing email in pref
        edit.putString(KEY_TOKEN, mobile);

        // commit changes
        edit.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
        }

    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // email
        user.put(KEY_EMAIL, Functions.decrypt(billoooSession.getString(KEY_EMAIL, null)));

        // token
        user.put(KEY_TOKEN, Functions.decrypt(billoooSession.getString(KEY_TOKEN, null)));

        // user userid
        user.put(KEY_USERID, Functions.decrypt(billoooSession.getString(KEY_USERID, null)));

        // user barcode
        user.put(KEY_BARCODE, Functions.decrypt(billoooSession.getString(KEY_BARCODE, null)));

        // return user
        return user;
    }



    public void clearStackToStartNewActivity(Context context,Intent intent){
        // Clearing all data from Shared Preferences

        // After logout redirect user to Loing Activity
        Intent i = intent;
        // Closing all the Activities
        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Staring Login Activity
        context.startActivity(i);

    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return billoooSession.getBoolean(IS_LOGIN, false);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isVerified(){
        return billoooSession.getBoolean(IS_VERIFIED, false);
    }

    /*
    * Change the status
    * */

    public void setIsSignup(){

        edit.putBoolean(IS_SIGNUP, true);
        edit.apply();
    }

    public void setIsLogin(){
        edit.putBoolean(IS_LOGIN, true);
        edit.apply();
    }

    public void setIsVerified(){
        edit.putBoolean(IS_VERIFIED, true);
        edit.apply();
    }

    /*
    * To get individual values
    * */

    public String getEmail()
    {
        return Functions.decrypt(billoooSession.getString(KEY_EMAIL, null));
    }

    public String getToken()
    {
        // token
        return Functions.decrypt(billoooSession.getString(KEY_TOKEN, null));
    }

    public String getUserID()
    {
        // user userid
        return Functions.decrypt(billoooSession.getString(KEY_USERID, null));
    }

    public String getBarcode()
    {
        // user barcode
        return Functions.decrypt(billoooSession.getString(KEY_BARCODE, ERROR_CODE_IN_CASE));
    }

    public boolean getIsSignUp()
    {
        return billoooSession.getBoolean(IS_SIGNUP,false);
    }

    public boolean getIsLogin()
    {
        return billoooSession.getBoolean(IS_LOGIN,false);
    }

    public boolean getIsVerify()
    {
        return billoooSession.getBoolean(IS_VERIFIED,false);
    }
}