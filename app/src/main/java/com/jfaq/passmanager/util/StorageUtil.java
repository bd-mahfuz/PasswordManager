package com.jfaq.passmanager.util;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageUtil {


    private final String STORAGE = "com.pm.STORAGE";
    private SharedPreferences preferences;
    private Context context;

    public StorageUtil(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }

    public void savePin(String pin) {
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pin", pin);
        editor.apply();
    }

    public String getPin() {
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        return preferences.getString("pin", null);
    }


}
