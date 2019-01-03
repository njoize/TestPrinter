package com.projectshoponline.app_new_tabhost;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserHelper {

    Context context;
    SharedPreferences sharedPerfs;
    Editor editor;

    // Prefs Keys
    static String perfsName = "UserHelper";
    static int perfsMode = 0;

    public UserHelper(Context context) {
        this.context = context;
        this.sharedPerfs = this.context.getSharedPreferences(perfsName, perfsMode);
        this.editor = sharedPerfs.edit();
    }

    public void createSession(String sMemberID, String sMemberName, String sMemberPass) {

        editor.putBoolean("LoginStatus", true);
        editor.putString("MemberID", sMemberID);
        editor.putString("MemberName", sMemberName);
        editor.putString("MemberPass", sMemberPass);

        editor.commit();
    }

    public void deleteSession() {
        editor.clear();
        editor.commit();
    }

    public boolean getLoginStatus() {
        return sharedPerfs.getBoolean("LoginStatus", false);
    }

    public String getMemberID() {
        return sharedPerfs.getString("MemberID", null);
    }

    public String getMemberName() {
        return sharedPerfs.getString("MemberName", null);
    }


    public String getMemberPass() {
        return sharedPerfs.getString("MemberPass", null);
    }
}