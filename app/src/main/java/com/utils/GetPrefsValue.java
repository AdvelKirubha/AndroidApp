package com.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by rajasingh on 4/29/2016.
 * Class to store and reterive value in SharedPreference.
 */
public class GetPrefsValue {
    SharedPreferences sPrefs;
    SharedPreferences.Editor sEdit;
    Context mContext;

    public GetPrefsValue(Context mContext){
        this.mContext = mContext;
    }

    public void setPrefs(String key, String value){
        sPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        sEdit = sPrefs.edit();
        sEdit.putString(key,value);
        sEdit.commit();
    }

    public String getPrefsValue(String key){
        String value = null;
        sPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        value = sPrefs.getString(key,null);
        return value;
    }
}
