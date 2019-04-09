package com.epic.ssb.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.epic.ssb.R;
import com.epic.ssb.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPref {

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor editor;
    private Context mContext;

    @Inject
    public AppPref(@ApplicationContext Context context) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(mContext.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void putImagePref(String key, byte[] value){
        try {
            editor = mPreferences.edit();
//			Base64.encodeToString(value, Base64.DEFAULT) is used to convert byte array to string..
            if(value == null){
                editor.putString(key, null);
            }else {
                editor.putString(key, Base64.encodeToString(value, Base64.DEFAULT));
            }
            editor.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getImagePref(String key){
        try {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String value =  mPreferences.getString(key, "");

        return Base64.decode(value, Base64.DEFAULT);
    }

    public void putImageUri(String uri, String key) {
        try {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(key, uri);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getImageUri(String key) {
        return mPreferences.getString(key, "");
    }
}
