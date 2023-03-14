package me.ahmedsmaha.project1.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class SharedPrefs {
    public static final String PREFS_Logger = "LoggerFile";
    int PRIVATE_MODE = 0;
    public static SharedPreferences pref;
    public static SharedPreferences.Editor editor;

    public SharedPrefs(@NonNull Context context) {
        pref = context.getSharedPreferences(PREFS_Logger, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setSignInInfo(String userName, String email) {
        editor.putString(Constants.KEY_USERNAME, userName);
        editor.putString(Constants.KEY_EMAIL, email);
        editor.commit();
    }

    public HashMap<String, String> getSessionInfo(){
        HashMap<String, String> userInfo= new HashMap<>();
        userInfo.put(Constants.KEY_USERNAME, pref.getString(Constants.KEY_USERNAME, null));
        userInfo.put(Constants.KEY_EMAIL, pref.getString(Constants.KEY_EMAIL, null));
        return userInfo;
    }
}
